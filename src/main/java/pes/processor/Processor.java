package pes.processor;

import pes.state.State;
import pes.state.Stateful;
import pes.task.RunningTask;

import java.util.Objects;

public class Processor implements Stateful {

  private final int ID;

  private RunningTask runningTask;

  public Processor(int ID) {
    this.ID = ID;
    runningTask = null;
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

  public int getID() {
    return ID;
  }

  public void executeOneCycle() {
    if (runningTask == null) {
      return;
    }
    runningTask.executeOneCycle();
    if (runningTask.isDone()) {
      setRunningTask(null);
    }
  }

  public boolean isIdle() {
    return runningTask == null;
  }

  @Override
  public State getState() {
    return new State(new String[] {"Running Task"}, new Object[] {runningTask});
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Processor processor = (Processor) o;
    return ID == processor.ID;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID);
  }
}
