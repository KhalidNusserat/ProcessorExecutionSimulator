import java.util.Arrays;

public class Multiprocessor {

    private final Processor[] processors;


    public Multiprocessor(int numberOfProcessors) {
        processors = new Processor[numberOfProcessors];
        for (int i = 0; i < numberOfProcessors; i++)
            processors[i] = new Processor(Integer.toString(i));
    }

    public void watchAllProcessors(GlobalLogger globalLogger) {
        for (Processor processor : processors)
            globalLogger.watchProcessor(processor);
    }

    public boolean isAllIdle() {
        return Arrays.stream(processors).allMatch(Processor::isIdle);
    }

    public void executeAllProcessors() {
        for (Processor processor : processors)
            processor.executeOneCycle();
    }
}