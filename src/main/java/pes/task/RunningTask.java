package pes.task;

import pes.processor.Processor;
import pes.state.State;
import pes.state.Stateful;

import java.util.Objects;

public class RunningTask implements Stateful {

  private final TaskMetadata metadata;

  private Processor processor;

  private int remainingTime;

  public RunningTask(TaskMetadata metadata) {
    if (metadata == null) {
      throw new IllegalArgumentException();
    }
    this.metadata = metadata;
    processor = null;
    remainingTime = metadata.getRequiredTime();
  }

  public int getRemainingTime() {
    return remainingTime;
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
    if (isDone()) {
      throw new IllegalStateException(
          "Cannot execute a finished task."); // TODO: find a better error to throw
    }
    remainingTime--;
  }

  @Override
  public String toString() {
    return "pes.task.RunningTask{" + "metadata=" + metadata + '}';
  }

  @Override
  public State getState() {
    return new State(new String[] {"Processor"}, new Object[] {processor});
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
