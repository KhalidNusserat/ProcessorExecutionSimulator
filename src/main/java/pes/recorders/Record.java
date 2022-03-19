package pes.recorders;

import pes.state.State;

public class Record {

  private final State state;

  private final int start;

  private int end;

  public Record(State state, int start, int end) {
    this.state = state;
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "pes.logger.utils.Interval {"
        + "\n\tvalue="
        + state
        + ",\n\tstart="
        + start
        + ",\n\tend="
        + end
        + "\n}";
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public int getDuration() {
    return start - end + 1;
  }

  public void incrementEnd() {
    end++;
  }

  public Object getState() {
    return state;
  }
}
