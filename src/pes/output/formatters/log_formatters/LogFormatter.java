package pes.output.formatters.log_formatters;

import pes.logger.Logger;

import java.util.ArrayList;

public interface LogFormatter {
  String[][] formatLogs(Logger stateLogger);

  void setFields(ArrayList<String> fields);

  LogFormatter clone();
}
