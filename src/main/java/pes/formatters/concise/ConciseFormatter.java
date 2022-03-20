package pes.formatters.concise;

import pes.OutputFile;
import pes.formatters.Formatter;
import pes.simulation.recorders.Recorder;
import pes.simulation.recorders.StatefulType;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class ConciseFormatter implements Formatter { // TODO: is it really a factory?
  @Override
  public OutputFile formatRecords(Recorder stateRecorder) {
    if (stateRecorder.getType().equals(StatefulType.TASK)) {
      return new TaskConciseFormatter().formatRecords(stateRecorder);
    } else if (stateRecorder.getType().equals(StatefulType.PROCESSOR)) {
      return new ProcessorConciseFormatter().formatRecords(stateRecorder);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public ArrayList<OutputFile> formatAllRecords(AbstractCollection<Recorder> recorders) {
    ArrayList<OutputFile> outputFiles = new ArrayList<>();
    for (Recorder recorder : recorders) {
      outputFiles.add(formatRecords(recorder));
    }
    return outputFiles;
  }
}
