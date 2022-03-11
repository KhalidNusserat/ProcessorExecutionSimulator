import java.util.Objects;

public class ProcessorState {

    private final Task runningTask;


    public ProcessorState(Processor processor) {
        runningTask = processor.getRunningTask();
    }

    public Task getRunningTask() {
        return runningTask;
    }

    public boolean isIdle() {
        return runningTask == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorState that = (ProcessorState) o;
        return Objects.equals(runningTask, that.runningTask);
    }

    @Override
    public String toString() {
        if (!isIdle())
            return "{Running task #" + runningTask.getInfo().getID() + "}";
        else
            return "{Idle}";
    }
}
