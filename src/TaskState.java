import java.util.Objects;

public class TaskState {

  private final Processor processor;

  private final int remainingTime;

  public TaskState(RunningTask runningTask) {
    if (runningTask == null) throw new IllegalArgumentException();
    processor = runningTask.getProcessor();
    remainingTime = runningTask.getRemainingTime();
  }

  public Processor getProcessor() {
    return processor;
  }

  public int getRemainingTime() {
    return remainingTime;
  }

  public boolean isRunning() {
    return processor != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskState taskState = (TaskState) o;
    return Objects.equals(processor, taskState.processor);
  }

  @Override
  public String toString() {
    return "TaskState {" + " processor=" + processor + ", remainingTime=" + remainingTime + "}";
  }
}
