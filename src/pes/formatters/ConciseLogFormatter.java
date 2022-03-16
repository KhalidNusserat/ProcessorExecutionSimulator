package pes.formatters;

import pes.logger.Interval;
import pes.logger.Logger;
import pes.logger.State;

import java.util.ArrayList;
import java.util.List;

public class ConciseLogFormatter implements LogFormatter {

  private ArrayList<ArrayList<String>> result;

  private ArrayList<String> properties;

  private void initialise() {
    result = new ArrayList<>();
    ArrayList<String> row = new ArrayList<>(List.of("From", "To"));
    row.addAll(properties);
    result.add(row);
  }

  private void addStateColumn(Interval<State> interval) {
    State state = interval.getValue();
    for (String property : state) {
      result.get(result.size() - 1).add(state.getValueOf(property));
    }
  }

  @Override
  public ConciseLogFormatter clone() {
    return new ConciseLogFormatter();
  }

  @Override
  public void setProperties(ArrayList<String> properties) {
    this.properties = properties;
  }

  @Override
  public String[][] formatLogs(Logger logger) {
    initialise();
    for (Interval<State> interval : logger) {
      result.add(new ArrayList<>());
      result.get(result.size() - 1).add(Integer.toString(interval.getStart()));
      result.get(result.size() - 1).add(Integer.toString(interval.getEnd()));
      addStateColumn(interval);
    }
    return result.stream().map(row -> row.toArray(new String[0])).toArray(String[][]::new);
  }
}
