package pes.output.summarisers;

import pes.recorders.GlobalRecorder;

public interface Summariser {
  String[][] getSummary(GlobalRecorder globalRecorder);
}
