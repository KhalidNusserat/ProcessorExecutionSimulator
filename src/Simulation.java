import java.util.ArrayList;

public class Simulation {

  private final Clock clock;

  private final GlobalLogger globalLogger;

  private final Multiprocessor multiprocessor;

  private final TasksStream tasksStream;

  private final Scheduler scheduler;

  public Simulation(Scheduler scheduler, int numberOfProcessors) {
    this.scheduler = scheduler;
    clock = new Clock();
    globalLogger = new GlobalLogger(clock);
    RunningTask.setGlobalLogger(globalLogger);
    Processor.setGlobalLogger(globalLogger);
    multiprocessor = new Multiprocessor(numberOfProcessors);
    tasksStream = new TasksStream(clock);
  }

  public void addTasks(ArrayList<RunningTask> tasks) {
    tasksStream.addTasks(tasks);
  }

  private void executeOneCycle() {
    tasksStream.issue(scheduler);
    scheduler.schedule(multiprocessor.getProcessors());
    multiprocessor.executeAllProcessors();
    globalLogger.captureAll();
    multiprocessor.removeFinishedTasks();
    clock.tick();
  }

  public boolean isFinished() {
    return multiprocessor.isAllIdle() && tasksStream.isEmpty() && scheduler.isEmpty();
  }

  public void runSimulation() {
    while (!isFinished()) executeOneCycle();
  }

  public GlobalLogger getGlobalLogger() {
    return globalLogger;
  }
}
