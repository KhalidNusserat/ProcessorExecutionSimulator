public interface Scheduler {
  void addTask(RunningTask runningTask);

  void schedule(Processor[] processors);

  boolean isEmpty();
}
