package pes.output.writers;

import pes.output.OutputFile;

import java.io.IOException;
import java.util.AbstractCollection;

public interface RecordWriter {
  void write(OutputFile outputFile) throws IOException;

  void writeAll(AbstractCollection<OutputFile> outputFiles) throws IOException;
}
