package pes.output.writers;

public class RecordWriterFactory {
  public RecordWriter createRecordWriter(String type) {
    if (type.equalsIgnoreCase("csv")) {
      return new CsvRecordWriter();
    } else {
      throw new IllegalArgumentException("Invalid extension specified");
    }
  }
}
