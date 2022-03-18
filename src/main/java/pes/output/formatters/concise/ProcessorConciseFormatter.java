package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.RecordFormatter;
import pes.recorders.Record;
import pes.recorders.Recorder;
import pes.task.RunningTask;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class ProcessorConciseFormatter implements RecordFormatter {

  @Override
  public OutputFile formatRecords(Recorder processorRecorder) {
    OutputFile outputFile = new OutputFile("Processor", "From", "To", "Running Task");
    for (Record record : processorRecorder) {
      outputFile.append(record.getStart());
      outputFile.append(record.getEnd());
      RunningTask runningTask = (RunningTask) record.getValueOf("Running Task");
      if (runningTask == null) {
        outputFile.append("Idle");
      } else {
        outputFile.append("Running task " + runningTask.getMetadata().getID());
      }
    }
    return outputFile;
  }

  @Override
  public AbstractCollection<OutputFile> formatAllRecords(AbstractCollection<Recorder> recorders) {
    ArrayList<OutputFile> result = new ArrayList<>();
    for (Recorder recorder : recorders) {
      result.add(formatRecords(recorder));
    }
    return result;
  }
}
