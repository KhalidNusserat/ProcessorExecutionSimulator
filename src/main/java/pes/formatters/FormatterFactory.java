package pes.formatters;

import pes.formatters.concise.ConciseFormatter;

public class FormatterFactory {
  public Formatter create(String type) {
    if (type.equalsIgnoreCase("concise")) {
      return new ConciseFormatter();
    } else {
      throw new IllegalArgumentException("Invalid formatter specified.");
    }
  }
}
