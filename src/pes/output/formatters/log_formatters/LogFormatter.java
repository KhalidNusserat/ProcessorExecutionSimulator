package pes.output.formatters.log_formatters;

import pes.logger.Logger;
import pes.output.OutputFile;

import java.util.ArrayList;

public interface LogFormatter {
  OutputFile formatLogs(Logger stateLogger);
}
