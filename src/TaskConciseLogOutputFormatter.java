public class TaskConciseLogOutputFormatter
    extends ConciseLogOutputFormatter<RunningTask, TaskState> {
  @Override
  public void addStateColumn(Interval<TaskState> interval) {
    result
        .get(result.size() - 1)
        .add(
            interval.getValue().getProcessor() == null
                ? "Idle/Not issued"
                : "Running on processor " + interval.getValue().getProcessor().getID());
  }
}
