package pes.schedulers;

import pes.processor.Processor;
import pes.state.Stateful;
import pes.task.RunningTask;

import java.util.AbstractCollection;
import java.util.ArrayList;

public interface Scheduler {
  void addTask(RunningTask runningTask);

  void schedule(AbstractCollection<Processor> processors);

  boolean isEmpty();

  ArrayList<Stateful> getRunningTasks();
}
