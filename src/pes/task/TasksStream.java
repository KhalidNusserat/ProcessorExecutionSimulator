package pes.task;

import pes.Clock;
import pes.schedulers.Scheduler;
import pes.task.RunningTask;

import java.util.ArrayList;
import java.util.HashSet;

public class TasksStream {

  private final HashSet<RunningTask> runningTasks = new HashSet<>();

  private final Clock clock;

  public TasksStream(Clock clock) {
    this.clock = clock;
  }

  public void addTasks(ArrayList<RunningTask> runningTasks) {
    this.runningTasks.addAll(runningTasks);
  }

  public boolean isEmpty() {
    return runningTasks.isEmpty();
  }

  public void issue(Scheduler scheduler) {
    ArrayList<RunningTask> toRemove = new ArrayList<>();
    for (RunningTask runningTask : runningTasks) {
      if (runningTask.getMetadata().getCreationTime() == clock.getTime()) {
        scheduler.addTask(runningTask);
        toRemove.add(runningTask);
      }
    }
    toRemove.forEach(runningTasks::remove);
  }
}
