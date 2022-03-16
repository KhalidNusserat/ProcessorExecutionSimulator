import java.io.IOException;

public interface LogWriter {
  void writeToFile(String path, String[][] data) throws IOException;
}
