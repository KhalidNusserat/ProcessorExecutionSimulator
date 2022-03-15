import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestRemainingTimeFirst implements Scheduler {
  private final PriorityQueue<RunningTask> runningTasks =
      new PriorityQueue<>(new TasksComparator());

  @Override
  public void addTask(RunningTask runningTask) {
    runningTasks.add(runningTask);
  }

  @Override
  public void schedule(Processor[] processors) {
    for (Processor processor : processors) {
      if (runningTasks.isEmpty()) return;
      if (processor.isIdle()) {
        processor.setRunningTask(runningTasks.poll());
      } else if (processor.getRunningTask().getMetadata().getPriority() == TaskPriority.LOW
          && runningTasks.peek().getMetadata().getPriority() == TaskPriority.HIGH) {
        runningTasks.add(processor.getRunningTask());
        processor.setRunningTask(runningTasks.poll());
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return runningTasks.isEmpty();
  }

  private static class TasksComparator implements Comparator<RunningTask> {

    @Override
    public int compare(RunningTask o1, RunningTask o2) {
      TaskPriority priority1 = o1.getMetadata().getPriority();
      TaskPriority priority2 = o2.getMetadata().getPriority();
      if (priority1 == TaskPriority.HIGH && priority2 == TaskPriority.LOW) return 1;
      else if (priority1 == TaskPriority.LOW && priority2 == TaskPriority.HIGH) return -1;
      else return o1.getRemainingTime() - o2.getRemainingTime();
    }
  }
}
