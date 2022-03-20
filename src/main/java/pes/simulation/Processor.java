package pes.simulation;

import pes.simulation.recorders.Stateful;

import java.util.Objects;

public class Processor implements Stateful {

  private final int ID;

  private Task task;

  public Processor(int ID) {
    this.ID = ID;
    task = null;
  }

  public Processor(Processor processor) {
    this.ID = processor.ID;
    this.task = processor.task;
  }

  public Task getRunningTask() {
    return task;
  }

  public void setRunningTask(Task task) {
    if (this.task != null) {
      this.task.setProcessor(null);
    }
    this.task = task;
    if (this.task != null) {
      this.task.setProcessor(this);
    }
  }

  public int getID() {
    return ID;
  }

  public void executeOneCycle() {
    if (task == null) {
      return;
    }
    task.executeOneCycle();
    if (task.isDone()) {
      setRunningTask(null);
    }
  }

  public boolean isIdle() {
    return task == null;
  }

  public String getTaskStateAsString() {
    if (isIdle()) {
      return "Idle";
    } else {
      return "Running task " + task.getID();
    }
  }

  @Override
  public Stateful getState() {
    return new Processor(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Processor processor = (Processor) o;
    return ID == processor.ID && Objects.equals(task, processor.task);
  }
}
