package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.RecordFormatter;
import pes.processor.Processor;
import pes.processor.ProcessorState;
import pes.recorders.Record;
import pes.recorders.Recorder;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class ProcessorConciseFormatter implements RecordFormatter {
  @Override
  public OutputFile formatRecords(Recorder processorRecorder) {
    OutputFile outputFile = new OutputFile("Processor", "From", "To", "Running Task");
    boolean isNameSet = false;
    for (Record record : processorRecorder) {
      outputFile.append(record.getStart());
      outputFile.append(record.getEnd());
      ProcessorState processorState = (ProcessorState) record.getState();
      Processor processor = (Processor) processorState.getObject();
      if (!isNameSet) {
        outputFile.setFileName("Processor" + processor.getID());
        isNameSet = true;
      }
      if (processorState.getRunningTask() == null) {
        outputFile.append("Idle");
      } else {
        outputFile.append("Running task " + processorState.getRunningTask().getID());
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
