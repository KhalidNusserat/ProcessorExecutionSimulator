import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvLogWriter implements LogWriter {
  @Override
  public void writeToFile(String path, String[][] data) throws IOException {
    path += ".csv";
    File file = new File(path);
    if (!file.createNewFile()) {
      throw new IOException("Could not create file " + path);
    }
    FileWriter fileWriter = new FileWriter(file);
    for (String[] row : data) {
      for (int j = 0; j < data[0].length; j++) {
        fileWriter.write(row[j]);
        if (j < data[0].length - 1) fileWriter.write(',');
      }
      fileWriter.write('\n');
    }
    fileWriter.close();
  }
}
