public class Processor implements Stateful<ProcessorState> {

    private final String ID;

    private final Logger<Processor, ProcessorState> logger;

    private Task runningTask;


    public Processor(String ID, Clock clock) {
        if (ID == null || clock == null)
            throw new IllegalArgumentException();
        this.ID = ID;
        logger = new Logger<>(this, clock);
        runningTask = null;
    }

    public Task getRunningTask() {
        return runningTask;
    }

    public void setRunningTask(Task runningTask) {
        if (this.runningTask != null)
            this.runningTask.setProcessor(null);
        this.runningTask = runningTask;
        this.runningTask.setProcessor(this);
    }

    public String getID() {
        return ID;
    }

    public Logger<Processor, ProcessorState> getLogger() {
        return logger;
    }

    public void executeOneCycle() {
        runningTask.executeOneCycle();
        logger.capture();
    }

    @Override
    public ProcessorState getState() {
        return new ProcessorState(this);
    }
}
