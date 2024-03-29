package pes.simulation.recorders;

import java.util.Iterator;
import java.util.Objects;

public class Recorder implements Iterable<Record> {
  private final StatefulType type;

  private final Stateful subject;

  private final Records records = new Records();

  public Recorder(StatefulType type, Stateful subject) {
    this.subject = subject;
    this.type = type;
  }

  public StatefulType getType() {
    return type;
  }

  public void record(int clockCycle) {
    records.add(subject.getState(), clockCycle);
  }

  public Stateful getSubject() {
    return subject;
  }

  @Override
  public String toString() {
    return records.toString();
  }

  @Override
  public Iterator<Record> iterator() {
    return records.iterator();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Recorder recorder = (Recorder) o;
    return subject.equals(recorder.subject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subject);
  }
}
