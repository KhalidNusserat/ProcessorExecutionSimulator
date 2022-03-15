public class RunningTask implements Stateful<TaskState> {

    private final TaskMetadata metadata;

    private Processor processor;

    private int remainingTime;


    public RunningTask(TaskMetadata metadata) {
        if (metadata == null)
            throw new IllegalArgumentException();
        this.metadata = metadata;
        processor = null;
        remainingTime = metadata.getRequiredTime();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public Processor getProcessor() {
        return processor;
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
        if (isDone())
            throw new IllegalStateException("Cannot execute a finished task."); // TODO: find a better error to throw
        remainingTime--;
    }

    @Override
    public TaskState getState() {
        return new TaskState(this);
    }
}
