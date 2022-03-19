package pes.simulation;

import pes.processor.Processor;
import pes.recorders.GlobalRecorder;
import pes.recorders.Recorder;
import pes.schedulers.Scheduler;
import pes.state.Stateful;
import pes.task.Task;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Simulation {

  private final Clock clock;

  private final TasksStream tasksStream;
  private final GlobalRecorder globalRecorder;
  private Scheduler scheduler;
  private ArrayList<Processor> processors;

  public Simulation() {
    clock = new Clock();
    tasksStream = new TasksStream(clock);
    globalRecorder = new GlobalRecorder();
  }

  public void setScheduler(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  public void createProcessors(int numberOfProcessors) {
    processors = new ArrayList<>();
    for (int i = 0; i < numberOfProcessors; i++) {
      Processor processor = new Processor(i);
      globalRecorder.watch(processor, "Processor");
      processors.add(processor);
    }
  }

  public void addTasks(AbstractCollection<Task> tasks) {
    tasksStream.addTasks(tasks);
    for (Task task : tasks) {
      globalRecorder.watch(task, "Running Task");
    }
  }

  private boolean areAllProcessorsIdle() {
    return processors.stream().allMatch(Processor::isIdle);
  }

  public boolean isFinished() {
    return areAllProcessorsIdle() && tasksStream.isEmpty() && scheduler.isEmpty();
  }

  public ArrayList<Stateful> getProcessors() {
    return new ArrayList<>(processors);
  }

  public ArrayList<Stateful> getRunningTasks() {
    return scheduler.getRunningTasks();
  }

  public int getClockCyclesCount() {
    return clock.getClockCyclesCount();
  }

  public void executeOneCycle() {
    tasksStream.issue(scheduler);
    scheduler.schedule(processors);
    globalRecorder.recordAll(clock.getClockCyclesCount());
    for (Processor processor : processors) {
      processor.executeOneCycle();
    }
    clock.tick();
  }

  public ArrayList<Recorder> getRecorders() {
    return globalRecorder.getRecorders();
  }

  public void run() {
    while (!isFinished()) {
      executeOneCycle();
    }
  }
}
