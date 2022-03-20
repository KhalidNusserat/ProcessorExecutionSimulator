package pes.simulation;

public class Clock {

  private int clockCyclesCount;

  public Clock() {
    clockCyclesCount = 0;
  }

  public void tick() {
    clockCyclesCount++;
  }

  public int getClock() {
    return clockCyclesCount;
  }
}
