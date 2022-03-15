public class Logger<SubjectType extends Stateful<State>, State> {

    private SubjectType subject;

    private final Clock clock;

    private final StateHistory<State> stateHistory = new StateHistory<>();


    public Logger(SubjectType subject, Clock clock) {
        this.subject = subject;
        this.clock = clock;
    }

    public SubjectType getSubject() {
        return subject;
    }

    public void setSubject(SubjectType subject) {
        this.subject = subject;
    }

    public void capture() {
        stateHistory.add(subject.getState(), clock.getTime());
    }

    @Override
    public String toString() {
        return stateHistory.toString();
    }
}
