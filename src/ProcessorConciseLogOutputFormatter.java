public class ProcessorConciseLogOutputFormatter
    extends ConciseLogOutputFormatter<Processor, ProcessorState> {

  @Override
  public void addStateColumn(Interval<ProcessorState> interval) {
    result
        .get(result.size() - 1)
        .add(
            interval.getValue().isIdle()
                ? "Idle"
                : "Running task " + interval.getValue().getRunningTask().getMetadata().getID());
  }
}
