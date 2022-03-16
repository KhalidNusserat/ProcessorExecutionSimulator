package pes.logger;

import java.util.ArrayList;
import java.util.Iterator;

public class StateHistory<State> implements Iterable<Interval<State>> {

  private final ArrayList<Interval<State>> stateHistory = new ArrayList<>();

  public void add(State state, int time) {
    if (state == null) throw new IllegalArgumentException();
    if (stateHistory.isEmpty()) {
      stateHistory.add(new Interval<>(state, time, time));
    } else if (stateHistory.get(stateHistory.size() - 1).getValue().equals(state)) {
      stateHistory.get(stateHistory.size() - 1).incrementEnd();
    } else {
      stateHistory.add(new Interval<>(state, time, time));
    }
  }

  public boolean isEmpty() {
    return stateHistory.isEmpty();
  }

  @Override
  public String toString() {
    return "pes.logger.StateHistory: " + stateHistory;
  }

  @Override
  public Iterator<Interval<State>> iterator() {
    return stateHistory.iterator();
  }
}
