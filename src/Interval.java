public class Interval<E> {

    private E value;

    private int start;

    private int end;


    @Override
    public String toString() {
        return "Interval {" +
                "\n\tvalue=" + value +
                ",\n\tstart=" + start +
                ",\n\tend=" + end +
                "\n}";
    }

    public Interval(E value, int start, int end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void incrementEnd() {
        end++;
    }
}
