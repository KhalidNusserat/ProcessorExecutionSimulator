package pes.processor;

import pes.state.State;
import pes.task.Task;

import java.util.Objects;

public class ProcessorState implements State {
  private final Processor object;

  private final Task task;

  public ProcessorState(Processor object) {
    this.object = object;
    this.task = object.getRunningTask();
  }

  @Override
  public Object getObject() {
    return object;
  }

  public Task getRunningTask() {
    return task;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProcessorState that = (ProcessorState) o;
    return object.equals(that.object) && Objects.equals(task, that.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(object, task);
  }
}
