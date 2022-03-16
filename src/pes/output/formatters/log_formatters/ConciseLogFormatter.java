package pes.output.formatters.log_formatters;

import pes.logger.utils.Interval;
import pes.logger.Logger;
import pes.logger.state.State;
import pes.output.OutputFile;

import java.util.ArrayList;
import java.util.List;

public class ConciseLogFormatter implements LogFormatter {

  @Override
  public OutputFile formatLogs(Logger logger) {
    // TODO: this is ugly, fix it
    ArrayList<ArrayList<String>> data = new ArrayList<>();
    ArrayList<String> firstRow = new ArrayList<>(List.of("From", "To"));
    firstRow.addAll(List.of(logger.getSubjectFields()));
    data.add(firstRow);
    for (Interval<State> interval : logger) {
      data.add(new ArrayList<>());
      data.get(data.size() - 1).add(Integer.toString(interval.getStart()));
      data.get(data.size() - 1).add(Integer.toString(interval.getEnd()));
      for (String field : interval.getValue().getFields()) {
        data.get(data.size() - 1).add(interval.getValue().getValueOf(field));
      }
    }
    return new OutputFile(logger.getSubjectType(), data);
  }
}
