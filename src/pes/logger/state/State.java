package pes.logger.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class State implements Iterable<String> {
  private final HashMap<String, String> fieldValue = new HashMap<>();

  private final String type;

  public State(String[] fields, String[] values, String type) {
    if (type == null) throw new IllegalArgumentException();
    if (fields.length != values.length)
      throw new IllegalArgumentException("Number of fields must be equal to number of values");
    this.type = type;
    for (int i = 0; i < fields.length; i++) {
      fieldValue.put(fields[i], values[i]);
    }
  }

  public String getValueOf(String property) {
    return fieldValue.get(property);
  }

  public String getType() {
    return type;
  }

  public String[] getFields() {
    return fieldValue.keySet().toArray(new String[0]);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (!other.getClass().getName().equals(this.getClass().getName())) return false;
    State otherState = (State) other;
    for (String property : fieldValue.keySet()) {
      if (!fieldValue.get(property).equals(otherState.getValueOf(property))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Iterator<String> iterator() {
    return fieldValue.keySet().iterator();
  }
}
