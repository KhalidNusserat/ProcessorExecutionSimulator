package pes.processor;

import pes.logger.GlobalLogger;
import pes.logger.state.State;
import pes.logger.state.StateBuilder;
import pes.logger.state.Stateful;
import pes.task.RunningTask;

import java.util.Objects;

public class Processor implements Stateful {

  private static GlobalLogger globalLogger;
  private final String ID;
  private RunningTask runningTask;

  public Processor(String ID) {
    if (ID == null) {
      throw new IllegalArgumentException();
    }
    this.ID = ID;
    runningTask = null;
    globalLogger.watch(this);
  }

  public static void setGlobalLogger(GlobalLogger globalLogger) {
    Processor.globalLogger = globalLogger;
  }

  public RunningTask getRunningTask() {
    return runningTask;
  }

  public void setRunningTask(RunningTask runningTask) {
    if (this.runningTask != null) {
      this.runningTask.setProcessor(null);
    }
    this.runningTask = runningTask;
    if (this.runningTask != null) {
      this.runningTask.setProcessor(this);
    }
  }

  public String getID() {
    return ID;
  }

  public void executeOneCycle() {
    if (runningTask == null) {
      return;
    }
    runningTask.executeOneCycle();
  }

  public boolean isIdle() {
    return runningTask == null;
  }

  @Override
  public State getState() {
    StateBuilder processorStateBuilder = new StateBuilder("processor", "runningTask");
    String runningTaskState = "Idle";
    if (!isIdle()) runningTaskState = "Running task " + runningTask.getMetadata().getID();
    return processorStateBuilder.getNewState(runningTaskState);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Processor processor = (Processor) o;
    return ID.equals(processor.ID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID);
  }
}
