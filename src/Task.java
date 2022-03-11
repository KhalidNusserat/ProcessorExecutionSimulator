public class Task implements Stateful<TaskState> {

    private final TaskInfo info;

    private Processor processor;

    private int remainingTime;

    private final Logger<Task, TaskState> logger;


    public Task(TaskInfo info, Clock clock) {
        if (info == null || clock == null)
            throw new IllegalArgumentException();
        this.info = info;
        processor = null;
        remainingTime = info.getRequiredTime();
        logger = new Logger<>(this, clock);
    }

    public Logger<Task, TaskState> getLogger() {
        return logger;
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

    public TaskInfo getInfo() {
        return info;
    }

    public boolean isDone() {
        return remainingTime == 0;
    }

    public void executeOneCycle() {
        if (isDone())
            throw new IllegalStateException("Cannot execute a finished task."); // TODO: find a better error to throw
        remainingTime--;
        logger.capture();
    }

    @Override
    public TaskState getState() {
        return new TaskState(this);
    }
}
