package pes.writers;

import pes.OutputFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter implements OutputWriter {
  @Override
  public void write(OutputFile outputFile) throws IOException {
    File directories = new File(outputFile.getOutputPath());
    if (!directories.exists()) {
      if (!directories.mkdirs()) {
        throw new IOException("Could not create the path: " + outputFile.getOutputPath());
      }
    }
    File file = new File(outputFile.getOutputPath() + "/" + outputFile.getFileName() + ".csv");
    FileWriter fileWriter = new FileWriter(file);
    for (ArrayList<String> row : outputFile) {
      for (String col : row) {
        fileWriter.write(col + ",");
      }
      fileWriter.write("\n");
    }
    fileWriter.close();
  }

  @Override
  public void writeAll(ArrayList<OutputFile> outputFiles) throws IOException {
    for (OutputFile outputFile : outputFiles) {
      write(outputFile);
    }
  }
}
