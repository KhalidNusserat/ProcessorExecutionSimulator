package pes.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputFile implements Iterable<ArrayList<String>> {
  private final String type;

  private final ArrayList<ArrayList<String>> data = new ArrayList<>();

  private String path;

  public OutputFile(String type, String... columnNames) {
    this.type = type;
    data.add(new ArrayList<>(List.of(columnNames)));
    data.add(new ArrayList<>());
  }

  public String getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void append(String value) {
    data.get(data.size() - 1).add(value);
    if (data.get(data.size() - 1).size() == data.get(0).size()) {
      data.add(new ArrayList<>());
    }
  }

  public void append(int i) {
    append(Integer.toString(i));
  }

  @Override
  public String toString() {
    return data.toString();
  }

  @Override
  public Iterator<ArrayList<String>> iterator() {
    return data.iterator();
  }
}
