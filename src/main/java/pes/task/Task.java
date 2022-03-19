package pes.task;

import pes.processor.Processor;
import pes.state.Stateful;

import java.util.Objects;

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

  public Task(Task task) {
    if (task == null) {
      throw new IllegalArgumentException();
    }
    this.ID = task.ID;
    this.priority = task.priority;
    this.requiredTime = task.requiredTime;
    this.remainingTime = task.remainingTime;
    this.creationTime = task.creationTime;
    this.processor = task.processor;
    this.isCreated = task.isCreated;
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

  public int getRequiredTime() {
    return requiredTime;
  }

  public boolean isCreated() {
    return isCreated;
  }

  public void setCreated(boolean created) {
    isCreated = created;
  }

  public boolean isDone() {
    return remainingTime == 0;
  }

  public boolean isRunning() {
    return processor != null;
  }

  public String getProcessorStateAsString() {
    if (!isCreated) {
      return "Not created";
    } else if (isRunning()) {
      return "Running on processor " + processor.getID();
    } else if (!isDone()) {
      return "Not running";
    } else {
      return "Finished";
    }
  }

  public void executeOneCycle() {
    if (isDone()) {
      throw new IllegalStateException(
          "Cannot execute a finished task."); // TODO: find a better error to throw
    }
    remainingTime--;
  }

  @Override
  public Stateful getState() {
    return new Task(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return ID == task.ID
        && isCreated == task.isCreated
        && Objects.equals(processor, task.processor);
  }
}
