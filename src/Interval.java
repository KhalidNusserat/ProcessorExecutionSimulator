public class Interval<E> {

  private final E value;

  private final int start;

  private int end;

  public Interval(E value, int start, int end) {
    this.value = value;
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "Interval {" + "\n\tvalue=" + value + ",\n\tstart=" + start + ",\n\tend=" + end + "\n}";
  }

  public E getValue() {
    return value;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public void incrementEnd() {
    end++;
  }
}
