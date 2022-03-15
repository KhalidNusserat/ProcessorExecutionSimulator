import java.io.File;
import java.util.ArrayList;

public class OutputWriter {
  private final String path;

  private final ArrayList<Logger<RunningTask, TaskState>> tasksLoggers;

  private final ArrayList<Logger<Processor, ProcessorState>> processorsLoggers;

  public OutputWriter(String path, GlobalLogger globalLogger) {
    this.path = path;
    tasksLoggers = globalLogger.getRunningTasksLoggers();
    processorsLoggers = globalLogger.getProcessorsLoggers();
  }

  private void createDirectoryStructure() {
    File outputDirectory = new File(path + "/output");
    if (!outputDirectory.exists()) {
      outputDirectory.mkdir();
    }
    File processorDirectory = new File(path + "/output/processors");
    if (!processorDirectory.exists()) {
      processorDirectory.mkdir();
    }
    File tasksDirectory = new File(path + "/output/tasks");
    if (!tasksDirectory.mkdir()) {
      tasksDirectory.mkdir();
    }
  }

  private void writeProcessorsOutput() {
    for (int i = 0; i < tasksLoggers.size(); i++) {
      Logger<RunningTask, TaskState> logger = tasksLoggers.get(i);
      CsvLogWriter csvLogWriter = new CsvLogWriter();
      TaskConciseLogOutputFormatter formatter = new TaskConciseLogOutputFormatter();
      String taskPath = path + "/output/tasks/task" + i;
      csvLogWriter.writeToFile(taskPath, formatter.formatLogs(logger));
    }
  }

  private void writeTasksOutput() {
    for (int i = 0; i < processorsLoggers.size(); i++) {
      Logger<Processor, ProcessorState> logger = processorsLoggers.get(i);
      CsvLogWriter csvLogWriter = new CsvLogWriter();
      ProcessorConciseLogOutputFormatter formatter = new ProcessorConciseLogOutputFormatter();
      String processorPath = path + "/output/processors/processor" + i;
      csvLogWriter.writeToFile(processorPath, formatter.formatLogs(logger));
    }
  }

  public void writeOutput() {
    createDirectoryStructure();
    writeProcessorsOutput();
    writeTasksOutput();
  }
}
