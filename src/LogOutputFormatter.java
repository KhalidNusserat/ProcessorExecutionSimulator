public interface LogOutputFormatter<SubjectType extends Stateful<State>, State> {
  String[][] formatLogs(Logger<SubjectType, State> stateLogger);
}
