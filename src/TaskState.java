import java.util.Objects;

public class TaskState {

    private final Processor processor;

    private final int remainingTime;


    public TaskState(Task task) {
        if (task == null)
            throw new IllegalArgumentException();
        processor = task.getProcessor();
        remainingTime = task.getRemainingTime();
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
        return "TaskState {" +
                "\n\tprocessor=" + processor +
                ",\n\tremainingTime=" + remainingTime +
                "\n}";
    }
}
