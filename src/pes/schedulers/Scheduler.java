package pes.schedulers;

import pes.processor.Processor;
import pes.task.RunningTask;

public interface Scheduler {
  void addTask(RunningTask runningTask);

  void schedule(Processor[] processors);

  boolean isEmpty();
}
