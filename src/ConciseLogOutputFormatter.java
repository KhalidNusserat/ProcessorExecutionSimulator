import java.util.ArrayList;
import java.util.List;

public abstract class ConciseLogOutputFormatter<SubjectType extends Stateful<State>, State>
    implements LogOutputFormatter<SubjectType, State> {

  protected final ArrayList<ArrayList<String>> result = new ArrayList<>();

  public ConciseLogOutputFormatter() {
    result.add(new ArrayList<>(List.of("From", "To", "State")));
  }

  public abstract void addStateColumn(Interval<State> interval);

  @Override
  public String[][] formatLogs(Logger<SubjectType, State> logger) {
    for (Interval<State> interval : logger) {
      result.add(new ArrayList<>());
      result.get(result.size() - 1).add(Integer.toString(interval.getStart()));
      result.get(result.size() - 1).add(Integer.toString(interval.getEnd()));
      addStateColumn(interval);
    }
    return result.stream().map(row -> row.toArray(new String[0])).toArray(String[][]::new);
  }
}
