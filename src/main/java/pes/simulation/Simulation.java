package pes.simulation;

import pes.OutputFile;
import pes.SimulationConfiguration;
import pes.Summariser;
import pes.TasksReader;
import pes.formatters.Formatter;
import pes.formatters.FormatterFactory;
import pes.simulation.recorders.Recorder;
import pes.simulation.recorders.Recorders;
import pes.simulation.recorders.StatefulType;
import pes.simulation.schedulers.Scheduler;
import pes.simulation.schedulers.SchedulerFactory;
import pes.writers.OutputWriter;
import pes.writers.RecordWriterFactory;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class Simulation {

  private final Clock clock;

  private final TasksStream tasksStream;

  private final Recorders recorders;

  private final Scheduler scheduler;

  private final Formatter formatter;

  private final OutputWriter writer;

  private final String outputDirectory;

  private ArrayList<Processor> processors;

  public Simulation(SimulationConfiguration configuration) throws IOException {
    clock = new Clock();
    tasksStream = new TasksStream();
    recorders = new Recorders();
    createProcessors(configuration.getNumberOfProcessors());
    addTasks(TasksReader.readTasksFromFile(configuration.getInputPath()));
    scheduler = new SchedulerFactory().create(configuration.getSchedulerType());
    formatter = new FormatterFactory().create(configuration.getFormatterType());
    writer = new RecordWriterFactory().create(configuration.getWriterType());
    outputDirectory = configuration.getOutputDirectory();
  }

  private void createProcessors(int numberOfProcessors) {
    processors = new ArrayList<>();
    for (int i = 0; i < numberOfProcessors; i++) {
      Processor processor = new Processor(i);
      recorders.watch(processor, StatefulType.PROCESSOR);
      processors.add(processor);
    }
  }

  private void addTasks(AbstractCollection<Task> tasks) {
    tasksStream.addTasks(tasks);
    for (Task task : tasks) {
      recorders.watch(task, StatefulType.TASK);
    }
  }

  private boolean areAllProcessorsIdle() {
    return processors.stream().allMatch(Processor::isIdle);
  }

  private boolean isFinished() {
    return areAllProcessorsIdle() && tasksStream.isEmpty() && scheduler.isEmpty();
  }

  private void executeOneCycle() {
    scheduler.addTasks(tasksStream.getCreatedTasks(clock.getClock()));
    scheduler.schedule(processors);
    recorders.recordAll(clock.getClock());
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
    ArrayList<Recorder> recorders = this.recorders.getRecorders();
    ArrayList<OutputFile> outputFiles = formatter.formatAllRecords(recorders);
    outputFiles.forEach(outputFile -> outputFile.setOutputDirectory(outputDirectory));
    writer.writeAll(outputFiles);
    OutputFile summariserOutputFile = new Summariser().summarise(recorders);
    summariserOutputFile.setOutputDirectory(outputDirectory);
    writer.write(summariserOutputFile);
  }
}
