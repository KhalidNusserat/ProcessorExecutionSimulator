package pes.simulation.schedulers;

import pes.simulation.Processor;
import pes.simulation.Task;
import pes.simulation.TaskPriority;
import pes.simulation.recorders.Stateful;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestRemainingTimeFirst implements Scheduler {
  private final PriorityQueue<Task> tasks = new PriorityQueue<>(new TasksComparator());

  @Override
  public void addTask(Task task) {
    tasks.add(task);
  }

  @Override
  public void addTasks(ArrayList<Task> tasks) {
    tasks.addAll(tasks);
  }

  @Override
  public void schedule(AbstractCollection<Processor> processors) {
    for (Processor processor : processors) {
      if (tasks.isEmpty()) {
        return;
      }
      if (processor.isIdle()) {
        processor.setRunningTask(tasks.poll());
      } else if (processor.getRunningTask().getPriority().equals(TaskPriority.LOW)
          && tasks.peek().getPriority().equals(TaskPriority.HIGH)) {
        // TODO: this is ugly, fix it
        processor.getRunningTask().setProcessor(null);
        tasks.add(processor.getRunningTask());
        processor.setRunningTask(tasks.poll());
      }
    }
  }

  public void test() {
    ArrayList<Task> tasks = new ArrayList<>(this.tasks);
    tasks.sort(new TasksComparator());
    System.out.println(tasks);
  }

  @Override
  public boolean isEmpty() {
    return tasks.isEmpty();
  }

  @Override
  public ArrayList<Stateful> getRunningTasks() {
    return new ArrayList<>(tasks);
  }

  private static class TasksComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
      TaskPriority priority1 = o1.getPriority();
      TaskPriority priority2 = o2.getPriority();
      if (priority1 == TaskPriority.HIGH && priority2 == TaskPriority.LOW) return -1;
      else if (priority1 == TaskPriority.LOW && priority2 == TaskPriority.HIGH) return 1;
      else return o1.getRemainingTime() - o2.getRemainingTime();
    }
  }
}
