package pes.output.writers;

import pes.output.OutputFile;

import java.io.IOException;

public interface LogWriter {
  void write(OutputFile outputFile) throws IOException;
}
