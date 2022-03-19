package pes.task;

import pes.processor.Processor;
import pes.state.State;

import java.util.Objects;

public class TaskState implements State {
  private final Task object;

  private final Processor processor;

  private final boolean isCreated;

  public TaskState(Task object) {
    this.object = object;
    this.processor = object.getProcessor();
    this.isCreated = object.isCreated();
  }

  @Override
  public Object getObject() {
    return object;
  }

  public Processor getProcessor() {
    return processor;
  }

  public boolean isCreated() {
    return isCreated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskState taskState = (TaskState) o;
    return isCreated == taskState.isCreated
        && object.equals(taskState.object)
        && Objects.equals(processor, taskState.processor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(object, processor, isCreated);
  }
}
