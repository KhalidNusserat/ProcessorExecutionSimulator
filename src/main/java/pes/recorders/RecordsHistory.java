package pes.recorders;

import pes.state.State;

import java.util.ArrayList;
import java.util.Iterator;

public class RecordsHistory implements Iterable<Record> {

  private final ArrayList<Record> stateHistory = new ArrayList<>();

  public void add(State state, int time) {
    if (state == null) {
      throw new IllegalArgumentException();
    }
    if (stateHistory.isEmpty()) {
      stateHistory.add(new Record(state, time, time));
    } else if (stateHistory.get(stateHistory.size() - 1).getState().equals(state)) {
      stateHistory.get(stateHistory.size() - 1).incrementEnd();
    } else {
      stateHistory.add(new Record(state, time, time));
    }
  }

  @Override
  public String toString() {
    return "pes.logger.StateHistory: " + stateHistory;
  }

  @Override
  public Iterator<Record> iterator() {
    return stateHistory.iterator();
  }
}
