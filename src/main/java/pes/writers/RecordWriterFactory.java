package pes.writers;

public class RecordWriterFactory {
  public OutputWriter create(String type) {
    if (type.equalsIgnoreCase("csv")) {
      return new CSVWriter();
    } else {
      throw new IllegalArgumentException("Invalid extension specified");
    }
  }
}
