import java.util.ArrayList;

public class GlobalLogger {

  private final ArrayList<Logger<RunningTask, TaskState>> runningTasksLoggers = new ArrayList<>();

  private final ArrayList<Logger<Processor, ProcessorState>> processorsLoggers = new ArrayList<>();

  private final Clock clock;

  public GlobalLogger(Clock clock) {
    this.clock = clock;
  }

  public void watchTask(RunningTask runningTask) {
    for (Logger<RunningTask, TaskState> taskLogger : runningTasksLoggers)
      if (taskLogger.getSubject() == runningTask) return;
    runningTasksLoggers.add(new Logger<>(runningTask, clock));
  }

  public void watchProcessor(Processor processor) {
    for (Logger<Processor, ProcessorState> processorLogger : processorsLoggers)
      if (processorLogger.getSubject() == processor) return;
    processorsLoggers.add(new Logger<>(processor, clock));
  }

  public void captureAll() {
    for (Logger<RunningTask, TaskState> taskLogger : runningTasksLoggers) taskLogger.capture();
    for (Logger<Processor, ProcessorState> processorLogger : processorsLoggers)
      processorLogger.capture();
  }

  public ArrayList<Logger<Processor, ProcessorState>> getProcessorsLoggers() {
    return new ArrayList<>(processorsLoggers);
  }

  public ArrayList<Logger<RunningTask, TaskState>> getRunningTasksLoggers() {
    return new ArrayList<>(runningTasksLoggers);
  }
}
