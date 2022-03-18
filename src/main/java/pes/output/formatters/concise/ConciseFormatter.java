package pes.output.formatters.concise;

import pes.output.OutputFile;
import pes.output.formatters.RecordFormatter;
import pes.recorders.Recorder;

import java.util.AbstractCollection;
import java.util.Objects;

public class ConciseFormatter implements RecordFormatter { // TODO: is it really a factory?
  @Override
  public OutputFile formatRecords(Recorder stateRecorder) {
    OutputFile outputFile = null;
    if (Objects.equals(stateRecorder.getType(), "Processor")) {
      outputFile = new ProcessorConciseFormatter().formatRecords(stateRecorder);
    } else if (Objects.equals(stateRecorder.getType(), "Running Task")) {
      outputFile = new TaskConciseFormatter().formatRecords(stateRecorder);
    }
    return outputFile;
  }

  @Override
  public AbstractCollection<OutputFile> formatAllRecords(AbstractCollection<Recorder> recorders) {
    return null;
  }
}
