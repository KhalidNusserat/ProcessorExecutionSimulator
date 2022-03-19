package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.RecordFormatter;
import pes.recorders.Record;
import pes.recorders.Recorder;
import pes.task.Task;
import pes.task.TaskState;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class TaskConciseFormatter implements RecordFormatter {
  @Override
  public OutputFile formatRecords(Recorder taskRecorder) {
    OutputFile outputFile = new OutputFile("RunningTask", "From", "To", "Processor");
    boolean isNameSet = false;
    for (Record record : taskRecorder) {
      outputFile.append(record.getStart());
      outputFile.append(record.getEnd());
      TaskState taskState = (TaskState) record.getState();
      Task task = (Task) taskState.getObject();
      if (!isNameSet) {
        outputFile.setFileName("RunningTask" + task.getID());
        isNameSet = true;
      }
      if (taskState.getProcessor() == null && taskState.isCreated()) {
        outputFile.append("Waiting");
      } else if (taskState.getProcessor() == null) {
        outputFile.append("Not created");
      } else {
        outputFile.append("Running on processor " + taskState.getProcessor().getID());
      }
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
