package pes.output.formatters.summarisers;

import pes.logger.GlobalLogger;

public interface Summariser {
  String[][] getSummary(GlobalLogger globalLogger);
}
