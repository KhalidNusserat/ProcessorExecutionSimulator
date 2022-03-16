import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Logger implements Iterable<Interval<State>> {

  private final Stateful subject;

  private final Clock clock;

  private final StateHistory<State> stateHistory = new StateHistory<>();

  public Logger(Stateful subject, Clock clock) {
    this.subject = subject;
    this.clock = clock;
  }

  public String getSubjectType() {
    return subject.getState().getType();
  }

  public ArrayList<String> getSubjectProperties() {
    return subject.getState().getProperties();
  }

  public void capture() {
    stateHistory.add(subject.getState(), clock.getTime());
  }

  @Override
  public String toString() {
    return stateHistory.toString();
  }

  @Override
  public Iterator<Interval<State>> iterator() {
    return stateHistory.iterator();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Logger logger = (Logger) o;
    return subject.equals(logger.subject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subject);
  }
}
