package pes.simulation.schedulers;

import pes.simulation.processor.Processor;
import pes.simulation.task.Task;
import pes.state.Stateful;

import java.util.AbstractCollection;
import java.util.ArrayList;

public interface Scheduler {
  void addTask(Task task);

  void schedule(AbstractCollection<Processor> processors);

  boolean isEmpty();

  ArrayList<Stateful> getRunningTasks();
}
