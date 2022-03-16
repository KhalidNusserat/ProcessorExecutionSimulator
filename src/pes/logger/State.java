package pes.logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class State implements Iterable<String> {
  private final HashMap<String, String> propertyValue = new HashMap<>();

  private final String type;

  public State(String[] properties, String[] values, String type) {
    if (type == null) throw new IllegalArgumentException();
    if (properties.length != values.length)
      throw new IllegalArgumentException("Number of properties must be equal to number of values");
    this.type = type;
    for (int i = 0; i < properties.length; i++) {
      propertyValue.put(properties[i], values[i]);
    }
  }

  public String getValueOf(String property) {
    return propertyValue.get(property);
  }

  public String getType() {
    return type;
  }

  public ArrayList<String> getProperties() {
    return new ArrayList<>(propertyValue.keySet());
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (!other.getClass().getName().equals(this.getClass().getName())) return false;
    State otherState = (State) other;
    for (String property : propertyValue.keySet()) {
      if (!propertyValue.get(property).equals(otherState.getValueOf(property))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Iterator<String> iterator() {
    return propertyValue.keySet().iterator();
  }
}
