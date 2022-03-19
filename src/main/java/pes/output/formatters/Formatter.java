package pes.output.formatters;

import pes.output.OutputFile;
import pes.simulation.recorders.Recorder;

import java.util.AbstractCollection;
import java.util.ArrayList;

public interface Formatter {
  OutputFile formatRecords(Recorder stateRecorder);

  ArrayList<OutputFile> formatAllRecords(AbstractCollection<Recorder> recorders);
}
