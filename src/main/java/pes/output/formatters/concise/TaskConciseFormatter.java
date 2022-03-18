package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.RecordFormatter;
import pes.processor.Processor;
import pes.recorders.Record;
import pes.recorders.Recorder;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class TaskConciseFormatter implements RecordFormatter {
  @Override
  public OutputFile formatRecords(Recorder taskRecorder) {
    OutputFile outputFile = new OutputFile("Task", "From", "To", "Processor");
    for (Record record : taskRecorder) {
      outputFile.append(record.getStart());
      outputFile.append(record.getEnd());
      Processor processor = (Processor) record.getValueOf("Processor");
      if (processor == null) {
        outputFile.append("Not running");
      } else {
        outputFile.append("Running on processor " + processor.getID());
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
