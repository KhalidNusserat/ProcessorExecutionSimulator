package pes.task;

import pes.processor.Processor;
import pes.state.State;
import pes.state.Stateful;

public class Task implements Stateful {

  private final int ID;

  private final TaskPriority priority;
  private final int creationTime;
  private final int requiredTime;
  private Processor processor = null;
  private int remainingTime;
  private boolean isCreated = false;

  public Task(int ID, TaskPriority priority, int requiredTime, int creationTime) {
    if (priority == null) {
      throw new IllegalArgumentException();
    }
    this.ID = ID;
    this.priority = priority;
    this.requiredTime = requiredTime;
    this.remainingTime = requiredTime;
    this.creationTime = creationTime;
  }

  public Processor getProcessor() {
    return processor;
  }

  public void setProcessor(Processor processor) {
    this.processor = processor;
  }

  public int getRemainingTime() {
    return remainingTime;
  }

  public int getID() {
    return ID;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public int getCreationTime() {
    return creationTime;
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
  public State getState() {
    return new TaskState(this);
  }

  public int getRequiredTime() {
    return requiredTime;
  }

  public boolean isCreated() {
    return isCreated;
  }

  public void setCreated(boolean created) {
    isCreated = created;
  }
}
