package pes.task;

import pes.logger.GlobalLogger;
import pes.logger.State;
import pes.logger.StateBuilder;
import pes.logger.Stateful;
import pes.processor.Processor;

import java.util.Objects;

public class RunningTask implements Stateful {

  private static GlobalLogger globalLogger;
  private final TaskMetadata metadata;
  private Processor processor;
  private int remainingTime;

  public RunningTask(TaskMetadata metadata) {
    if (metadata == null) throw new IllegalArgumentException();
    this.metadata = metadata;
    processor = null;
    remainingTime = metadata.getRequiredTime();
    globalLogger.watch(this);
  }

  public static void setGlobalLogger(GlobalLogger globalLogger) {
    RunningTask.globalLogger = globalLogger;
  }

  public int getRemainingTime() {
    return remainingTime;
  }

  public Processor getProcessor() {
    return processor;
  }

  public void setProcessor(Processor processor) {
    this.processor = processor;
  }

  public TaskMetadata getMetadata() {
    return metadata;
  }

  public boolean isDone() {
    return remainingTime == 0;
  }

  public void executeOneCycle() {
    if (isDone())
      throw new IllegalStateException(
          "Cannot execute a finished task."); // TODO: find a better error to throw
    remainingTime--;
  }

  @Override
  public String toString() {
    return "pes.task.RunningTask{" + "metadata=" + metadata + '}';
  }

  @Override
  public State getState() {
    StateBuilder taskStateBuilder = new StateBuilder("runningTask", "processor", "remainingTime");
    String processorState = "Not running";
    if (processor != null) processorState = "Running on processor " + processor.getID();
    return taskStateBuilder.getNewState(processorState, Integer.toString(remainingTime));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RunningTask that = (RunningTask) o;
    return metadata.equals(that.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metadata);
  }
}
