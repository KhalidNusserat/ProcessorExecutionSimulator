public class Processor implements Stateful<ProcessorState> {

  private final String ID;

  private RunningTask runningTask;

  private static GlobalLogger globalLogger;

  public Processor(String ID) {
    if (ID == null) {
      throw new IllegalArgumentException();
    }
    this.ID = ID;
    runningTask = null;
    globalLogger.watchProcessor(this);
  }

  public RunningTask getRunningTask() {
    return runningTask;
  }

  public void setRunningTask(RunningTask runningTask) {
    if (this.runningTask != null) this.runningTask.setProcessor(null);
    this.runningTask = runningTask;
    this.runningTask.setProcessor(this);
  }

  public String getID() {
    return ID;
  }

  public static void setGlobalLogger(GlobalLogger globalLogger) {
    Processor.globalLogger = globalLogger;
  }

  public void executeOneCycle() {
    if (runningTask == null) {
      return;
    }
    runningTask.executeOneCycle();
    if (runningTask.getRemainingTime() == 0) {
      runningTask.setProcessor(null);
      runningTask = null;
    }
  }

  public boolean isIdle() {
    return runningTask == null;
  }

  @Override
  public ProcessorState getState() {
    return new ProcessorState(this);
  }
}
