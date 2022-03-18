package pes.state;

import java.util.HashMap;

public class State {
  private final HashMap<String, Object> fieldValue = new HashMap<>();

  public State(String[] fields, Object[] values) {
    if (fields == null || values == null) {
      throw new IllegalArgumentException();
    }
    if (fields.length != values.length) {
      throw new IllegalArgumentException("Number of fields must be equal to number of values");
    }
    for (int i = 0; i < fields.length; i++) {
      if (fields[i] == null) {
        throw new IllegalArgumentException();
      }
      fieldValue.put(fields[i], values[i]);
    }
  }

  public Object getValueOf(String property) {
    return fieldValue.get(property);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (!other.getClass().getName().equals(this.getClass().getName())) return false;
    State otherState = (State) other;
    for (String property : fieldValue.keySet()) {
      if (otherState.getValueOf(property) == null
          || !fieldValue.get(property).equals(otherState.getValueOf(property))) {
        return false;
      }
    }
    return true;
  }
}
