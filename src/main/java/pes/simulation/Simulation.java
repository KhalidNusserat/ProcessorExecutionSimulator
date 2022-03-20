package pes.simulation;

import pes.input.SimulationConfiguration;
import pes.output.OutputFile;
import pes.output.formatters.Formatter;
import pes.output.formatters.FormatterFactory;
import pes.output.summarisers.Summariser;
import pes.output.writers.RecordWriter;
import pes.output.writers.RecordWriterFactory;
import pes.simulation.processor.Processor;
import pes.simulation.recorders.GlobalRecorder;
import pes.simulation.recorders.Recorder;
import pes.simulation.schedulers.Scheduler;
import pes.simulation.schedulers.SchedulerFactory;
import pes.simulation.task.Task;
import pes.state.StatefulType;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class Simulation {

  private final Clock clock;

  private final TasksStream tasksStream;

  private final GlobalRecorder globalRecorder;

  private final Scheduler scheduler;

  private final Formatter formatter;

  private final RecordWriter writer;

  private final String outputDirectory;

  private ArrayList<Processor> processors;

  public Simulation(SimulationConfiguration configuration) {
    clock = new Clock();
    tasksStream = new TasksStream(clock);
    globalRecorder = new GlobalRecorder();
    createProcessors(configuration.getNumberOfProcessors());
    addTasks(configuration.getTasks());
    scheduler = new SchedulerFactory().create(configuration.getSchedulerType());
    formatter = new FormatterFactory().create(configuration.getFormatterType());
    writer = new RecordWriterFactory().create(configuration.getWriterType());
    outputDirectory = configuration.getOutputDirectory();
  }

  private void createProcessors(int numberOfProcessors) {
    processors = new ArrayList<>();
    for (int i = 0; i < numberOfProcessors; i++) {
      Processor processor = new Processor(i);
      globalRecorder.watch(processor, StatefulType.PROCESSOR);
      processors.add(processor);
    }
  }

  private void addTasks(AbstractCollection<Task> tasks) {
    tasksStream.addTasks(tasks);
    for (Task task : tasks) {
      globalRecorder.watch(task, StatefulType.TASK);
    }
  }

  private boolean areAllProcessorsIdle() {
    return processors.stream().allMatch(Processor::isIdle);
  }

  private boolean isFinished() {
    return areAllProcessorsIdle() && tasksStream.isEmpty() && scheduler.isEmpty();
  }

  private void executeOneCycle() {
    tasksStream.issue(scheduler);
    scheduler.schedule(processors);
    globalRecorder.recordAll(clock.getClockCyclesCount());
    for (Processor processor : processors) {
      processor.executeOneCycle();
    }
    clock.tick();
  }

  public void run() {
    while (!isFinished()) {
      executeOneCycle();
    }
  }

  public void writeOutput() throws IOException {
    ArrayList<Recorder> recorders = globalRecorder.getRecorders();
    ArrayList<OutputFile> outputFiles = formatter.formatAllRecords(recorders);
    outputFiles.forEach(outputFile -> outputFile.setOutputDirectory(outputDirectory));
    writer.writeAll(outputFiles);
    OutputFile summariserOutputFile = new Summariser().summarise(recorders);
    summariserOutputFile.setOutputDirectory(outputDirectory);
    writer.write(summariserOutputFile);
  }
}
