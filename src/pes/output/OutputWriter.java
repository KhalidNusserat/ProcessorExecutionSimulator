package pes.output;

import pes.formatters.LogFormatter;
import pes.logger.Logger;

import java.io.File;
import java.util.AbstractList;
import java.util.HashMap;

public class OutputWriter {
  private final LogWriter logWriter;

  private final LogFormatter logFormatter;

  private final HashMap<String, TypeOutputWriter> outputWriterHashMap = new HashMap<>();

  public OutputWriter(LogWriter logWriter, LogFormatter logFormatter) {
    this.logWriter = logWriter;
    this.logFormatter = logFormatter;
  }

  private void createDirectory(String path) throws Exception {
    File directory = new File(path);
    if (!directory.exists()) {
      if (!directory.mkdir()) throw new Exception("Could not create the directory " + path);
    }
  }

  public void writeLogs(AbstractList<Logger> loggers, String path) throws Exception {
    createDirectory(path + "/output");
    for (Logger logger : loggers) {
      String currentType = logger.getSubjectType();
      if (!outputWriterHashMap.containsKey(currentType)) {
        String basePath = path + "/output/" + currentType + "/";
        createDirectory(basePath);
        LogFormatter logFormatter = this.logFormatter.clone();
        logFormatter.setProperties(logger.getSubjectProperties());
        TypeOutputWriter typeOutputWriter =
            new TypeOutputWriter(logWriter, basePath + currentType, logFormatter);
        outputWriterHashMap.put(currentType, typeOutputWriter);
      }
      outputWriterHashMap.get(currentType).writeLogFile(logger);
    }
  }
}
