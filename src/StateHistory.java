import java.util.ArrayList;
import java.util.Iterator;

public class StateHistory<State> implements Iterable<Interval<State>> {

    private final ArrayList<Interval<State>> stateHistory = new ArrayList<>();


    public void add(State state, int time) {
        if (stateHistory.isEmpty()) {
            stateHistory.add(new Interval<>(state, time, time));
        }
        else if (stateHistory.get(stateHistory.size() - 1).getValue() == state) {
            stateHistory.get(stateHistory.size() - 1).incrementEnd();
        }
        else {
            stateHistory.add(new Interval<>(state, time, time));
        }
    }

    class StateHistoryIterator implements Iterator<Interval<State>> {

        private int index;


        public StateHistoryIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < stateHistory.size();
        }

        @Override
        public Interval<State> next() {
            if (index >= stateHistory.size())
                throw new IndexOutOfBoundsException();
            return stateHistory.get(index++);
        }
    }

    @Override
    public Iterator<Interval<State>> iterator() {
        return new StateHistoryIterator();
    }
}
