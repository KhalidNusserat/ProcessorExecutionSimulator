package pes.simulation.recorders;

import pes.state.Stateful;

import java.util.ArrayList;
import java.util.Iterator;

public class RecordsHistory implements Iterable<Record> {

  private final ArrayList<Record> stateHistory = new ArrayList<>();

  public void add(Stateful stateful, int time) {
    if (stateful == null) {
      throw new IllegalArgumentException();
    }
    if (stateHistory.isEmpty()) {
      stateHistory.add(new Record(stateful, time, time));
    } else if (stateHistory.get(stateHistory.size() - 1).getState().equals(stateful)) {
      stateHistory.get(stateHistory.size() - 1).incrementEnd();
    } else {
      stateHistory.add(new Record(stateful, time, time));
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
