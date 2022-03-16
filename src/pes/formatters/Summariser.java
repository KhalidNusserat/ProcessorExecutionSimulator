package pes.formatters;

import pes.logger.GlobalLogger;

public interface Summariser {
  String[][] getSummary(GlobalLogger globalLogger);
}
