package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.Formatter;
import pes.recorders.Record;
import pes.recorders.Recorder;
import pes.task.Task;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class TaskConciseFormatter implements Formatter {
  @Override
  public OutputFile formatRecords(Recorder taskRecorder) {
    OutputFile outputFile = new OutputFile("Tasks", "From", "To", "Processor");
    Task task = (Task) taskRecorder.getSubject();
    outputFile.setFileName("task" + task.getID());
    for (Record record : taskRecorder) {
      outputFile.append(record.getStart());
      outputFile.append(record.getEnd());
      Task taskState = (Task) record.getState();
      outputFile.append(taskState.getProcessorStateAsString());
    }
    return outputFile;
  }

  @Override
  public AbstractCollection<OutputFile> formatAllRecords(AbstractCollection<Recorder> recorders) {
    ArrayList<OutputFile> outputFiles = new ArrayList<>();
    for (Recorder recorder : recorders) {
      outputFiles.add(formatRecords(recorder));
    }
    return outputFiles;
  }
}
