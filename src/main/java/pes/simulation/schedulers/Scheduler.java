package pes.simulation.schedulers;

import pes.simulation.Processor;
import pes.simulation.Task;

import java.util.ArrayList;

public interface Scheduler {
  void addTask(Task task);

  void addTasks(ArrayList<Task> tasks);

  void schedule(ArrayList<Processor> processors);

  boolean isEmpty();
}
