import java.io.File;
import java.io.FileWriter;

public class CsvLogWriter implements LogWriter {
  @Override
  public void writeToFile(String path, String[][] data) {
    path += ".csv";
    try {
      File file = new File(path);
      if (file.exists())
        file.delete();
      file.createNewFile();
      FileWriter fileWriter = new FileWriter(file);
      for (String[] row : data) {
        for (int j = 0; j < data[0].length; j++) {
          fileWriter.write(row[j]);
          if (j < data[0].length - 1) fileWriter.write(',');
        }
        fileWriter.write('\n');
      }
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
