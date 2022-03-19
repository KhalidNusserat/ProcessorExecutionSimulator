package pes.schedulers;

import pes.processor.Processor;
import pes.state.Stateful;
import pes.task.Task;

import java.util.AbstractCollection;
import java.util.ArrayList;

public interface Scheduler {
  void addTask(Task task);

  void schedule(AbstractCollection<Processor> processors);

  boolean isEmpty();

  ArrayList<Stateful> getRunningTasks();
}
