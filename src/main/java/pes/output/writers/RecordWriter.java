package pes.output.writers;

import pes.output.OutputFile;

import java.io.IOException;
import java.util.ArrayList;

public interface RecordWriter {
  void write(OutputFile outputFile) throws IOException;

  void writeAll(ArrayList<OutputFile> outputFiles) throws IOException;
}
