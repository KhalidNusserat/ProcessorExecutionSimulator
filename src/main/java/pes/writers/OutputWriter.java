package pes.writers;

import pes.OutputFile;

import java.io.IOException;
import java.util.ArrayList;

public interface OutputWriter {
  void write(OutputFile outputFile) throws IOException;

  void writeAll(ArrayList<OutputFile> outputFiles) throws IOException;
}
