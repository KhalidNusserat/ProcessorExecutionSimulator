package pes.output.writers;

import pes.output.OutputFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvLogWriter implements LogWriter {
  @Override
  public void write(OutputFile outputFile) throws IOException {
    File file = new File(outputFile.getPath());
    if (!file.createNewFile()) {
      throw new IOException("Could not create file " + outputFile.getPath());
    }
    FileWriter fileWriter = new FileWriter(file);
    for (ArrayList<String> row : outputFile) {
      for (String col : row) {
        fileWriter.write(col + ".csv");
      }
      fileWriter.write("\n");
    }
    fileWriter.close();
  }
}
