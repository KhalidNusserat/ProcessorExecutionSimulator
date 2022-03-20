package pes.simulation.schedulers;

import pes.simulation.Processor;
import pes.simulation.Task;
import pes.simulation.TaskPriority;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class TasksComparator implements Comparator<Task> {

  @Override
  public int compare(Task o1, Task o2) {
    TaskPriority priority1 = o1.getPriority();
    TaskPriority priority2 = o2.getPriority();
    if (priority1 == TaskPriority.HIGH && priority2 == TaskPriority.LOW) return -1;
    else if (priority1 == TaskPriority.LOW && priority2 == TaskPriority.HIGH) return 1;
    else return o1.getRemainingTime() - o2.getRemainingTime();
  }
}

public class ShortestRemainingTimeFirst implements Scheduler {
  private final PriorityQueue<Task> tasks = new PriorityQueue<>(new TasksComparator());

  @Override
  public void addTask(Task task) {
    tasks.add(task);
  }

  @Override
  public void addTasks(ArrayList<Task> tasks) {
    for (Task task : tasks) {
      addTask(task);
    }
  }

  @Override
  public void schedule(ArrayList<Processor> processors) {
    for (Processor processor : processors) {
      if (tasks.isEmpty()) {
        return;
      }
      if (processor.isIdle()) {
        processor.setRunningTask(tasks.poll());
        return;
      }
      Task runningTask = processor.getRunningTask();
      Task queueTask = tasks.peek();
      TaskPriority runningTaskPriority = runningTask.getPriority();
      TaskPriority queueTaskPriority = queueTask.getPriority();
      if (runningTaskPriority.equals(TaskPriority.LOW)
          && queueTaskPriority.equals(TaskPriority.HIGH)) {
        tasks.add(processor.getRunningTask());
        processor.setRunningTask(tasks.poll());
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return tasks.isEmpty();
  }
}
