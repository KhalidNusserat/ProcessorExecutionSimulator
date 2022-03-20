package pes.simulation;

import java.util.ArrayList;
import java.util.HashSet;

public class TasksStream {

  private final HashSet<Task> tasks = new HashSet<>();

  public void addTasks(ArrayList<Task> tasks) {
    this.tasks.addAll(tasks);
  }

  public boolean isEmpty() {
    return tasks.isEmpty();
  }

  public ArrayList<Task> getCreatedTasks(int clockCycle) {
    ArrayList<Task> createdTasks = new ArrayList<>();
    for (Task task : tasks) {
      if (task.getCreationTime() == clockCycle) {
        task.setCreated(true);
        createdTasks.add(task);
      }
    }
    createdTasks.forEach(tasks::remove);
    return createdTasks;
  }
}
