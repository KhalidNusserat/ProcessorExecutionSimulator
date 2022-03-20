package pes.simulation.recorders;

import java.util.ArrayList;
import java.util.Iterator;

public class Records implements Iterable<Record> {

  private final ArrayList<Record> records = new ArrayList<>();

  public void add(Stateful stateful, int time) {
    if (stateful == null) {
      throw new IllegalArgumentException();
    }
    if (records.isEmpty()) {
      records.add(new Record(stateful, time, time));
    } else if (records.get(records.size() - 1).getState().equals(stateful)) {
      records.get(records.size() - 1).incrementEnd();
    } else {
      records.add(new Record(stateful, time, time));
    }
  }

  @Override
  public String toString() {
    return "pes.logger.StateHistory: " + records;
  }

  @Override
  public Iterator<Record> iterator() {
    return records.iterator();
  }
}
