package pes.simulation;

import pes.simulation.schedulers.Scheduler;
import pes.simulation.task.Task;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;

public class TasksStream {

  private final HashSet<Task> tasks = new HashSet<>();

  private final Clock clock;

  public TasksStream(Clock clock) {
    this.clock = clock;
  }

  public void addTasks(AbstractCollection<Task> tasks) {
    this.tasks.addAll(tasks);
  }

  public boolean isEmpty() {
    return tasks.isEmpty();
  }

  public void issue(Scheduler scheduler) {
    ArrayList<Task> toRemove = new ArrayList<>();
    for (Task task : tasks) {
      if (task.getCreationTime() == clock.getClockCyclesCount()) {
        task.setCreated(true);
        scheduler.addTask(task);
        toRemove.add(task);
      }
    }
    toRemove.forEach(tasks::remove);
  }
}
