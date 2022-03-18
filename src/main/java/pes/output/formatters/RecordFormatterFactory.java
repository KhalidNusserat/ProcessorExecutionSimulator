package pes.output.formatters;

import pes.output.formatters.concise.ConciseFormatter;

public class RecordFormatterFactory {
  public RecordFormatter createRecordFormatter(String type) {
    if (type.equalsIgnoreCase("concise")) {
      return new ConciseFormatter();
    } else {
      throw new IllegalArgumentException("Invalid formatter specified.");
    }
  }
}
