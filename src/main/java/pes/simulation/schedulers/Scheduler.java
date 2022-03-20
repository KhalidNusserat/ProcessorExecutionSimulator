package pes.simulation.schedulers;

import pes.simulation.Processor;
import pes.simulation.Task;
import pes.simulation.recorders.Stateful;

import java.util.AbstractCollection;
import java.util.ArrayList;

public interface Scheduler {
  void addTask(Task task);

  void addTasks(ArrayList<Task> tasks);

  void schedule(AbstractCollection<Processor> processors);

  boolean isEmpty();

  ArrayList<Stateful> getRunningTasks();
}
