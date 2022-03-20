package pes.output.writers;

public class RecordWriterFactory {
  public RecordWriter create(String type) {
    if (type.equalsIgnoreCase("csv")) {
      return new CSVWriter();
    } else {
      throw new IllegalArgumentException("Invalid extension specified");
    }
  }
}
