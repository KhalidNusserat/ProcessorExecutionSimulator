public interface Scheduler {
    void addTask(RunningTask runningTask);
    void schedule();
    boolean isDone();
}
