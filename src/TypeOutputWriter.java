import java.io.IOException;
import java.util.ArrayList;

public class TypeOutputWriter {
    private int count = 0;

    private final LogWriter logWriter;

    private final String basePath;

    private final LogFormatter logFormatter;

    public TypeOutputWriter(LogWriter logWriter, String basePath, LogFormatter logFormatter) {
        this.logWriter = logWriter;
        this.basePath = basePath;
        this.logFormatter = logFormatter;
    }

    public void writeLogFile(Logger logger) throws IOException {
        String path = basePath + count++;
        logWriter.writeToFile(path, logFormatter.formatLogs(logger));
    }
}
