package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.Formatter;
import pes.processor.Processor;
import pes.recorders.Record;
import pes.recorders.Recorder;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class ProcessorConciseFormatter implements Formatter {
  @Override
  public OutputFile formatRecords(Recorder processorRecorder) {
    OutputFile outputFile = new OutputFile("Processors", "From", "To", "Running Task");
    Processor processor = (Processor) processorRecorder.getSubject();
    outputFile.setFileName("processor" + processor.getID());
    for (Record record : processorRecorder) {
      outputFile.append(record.getStart());
      outputFile.append(record.getEnd());
      Processor processorState = (Processor) record.getState();
      outputFile.append(processorState.getTaskStateAsString());
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
