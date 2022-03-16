import java.util.ArrayList;

public interface LogFormatter {
  String[][] formatLogs(Logger stateLogger);

  void setProperties(ArrayList<String> properties);

  LogFormatter clone();
}
