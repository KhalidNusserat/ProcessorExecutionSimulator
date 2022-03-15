import java.util.ArrayList;

public class Simulation {

  private final Clock clock;

  private final GlobalLogger globalLogger;

  private final Multiprocessor multiprocessor;

  private final TasksSource tasksSource;

  private final Scheduler scheduler;

  public Simulation(Scheduler scheduler, int numberOfProcessors) {
    this.scheduler = scheduler;
    clock = new Clock();
    globalLogger = new GlobalLogger(clock);
    RunningTask.setGlobalLogger(globalLogger);
    Processor.setGlobalLogger(globalLogger);
    multiprocessor = new Multiprocessor(numberOfProcessors);
    tasksSource = new TasksSource(clock);
  }

  public void addTasks(ArrayList<TaskMetadata> tasks) {
    tasksSource.createNewTasks(tasks);
  }

  private void executeOneCycle() {
    tasksSource.issue(scheduler);
    scheduler.schedule(multiprocessor.getProcessors());
    multiprocessor.executeAllProcessors();
    globalLogger.captureAll();
    multiprocessor.removeFinishedTasks();
    clock.tick();
  }

  public boolean isFinished() {
    return multiprocessor.isAllIdle() && tasksSource.isEmpty() && scheduler.isEmpty();
  }

  public void runSimulation() {
    while (!isFinished()) executeOneCycle();
  }

  public GlobalLogger getGlobalLogger() {
    return globalLogger;
  }
}
