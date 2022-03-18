package pes.output.formatters;

import pes.output.OutputFile;
import pes.recorders.Recorder;

import java.util.AbstractCollection;

public interface RecordFormatter {
  OutputFile formatRecords(Recorder stateRecorder);

  AbstractCollection<OutputFile> formatAllRecords(AbstractCollection<Recorder> recorders);
}
