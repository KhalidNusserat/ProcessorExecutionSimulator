package pes.output;

import pes.formatters.LogFormatter;
import pes.logger.Logger;

import java.io.IOException;

public class TypeOutputWriter {
  private final LogWriter logWriter;
  private final String basePath;
  private final LogFormatter logFormatter;
  private int count = 0;

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
