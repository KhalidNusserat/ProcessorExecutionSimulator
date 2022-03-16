import java.util.Arrays;

public class Multiprocessor {

  private final Processor[] processors;

  public Multiprocessor(int numberOfProcessors) {
    processors = new Processor[numberOfProcessors];
    for (int i = 0; i < numberOfProcessors; i++) {
      processors[i] = new Processor(Integer.toString(i));
    }
  }

  public boolean isAllIdle() {
    return Arrays.stream(processors).allMatch(Processor::isIdle);
  }

  public void executeAllProcessors() {
    for (Processor processor : processors) processor.executeOneCycle();
  }

  public Processor[] getProcessors() {
    return processors;
  }

  public void removeFinishedTasks() {
    for (Processor processor : processors) {
      if (!processor.isIdle() && processor.getRunningTask().getRemainingTime() == 0) {
        processor.setRunningTask(null);
      }
    }
  }
}
