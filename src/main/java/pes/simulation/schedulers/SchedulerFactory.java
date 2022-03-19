package pes.simulation.schedulers;

public class SchedulerFactory {
  public Scheduler createScheduler(String type) {
    if (type.equalsIgnoreCase("srtf")) {
      return new ShortestRemainingTimeFirst();
    } else {
      throw new IllegalArgumentException("Invalid scheduler specified.");
    }
  }
}
