package pes.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputFile implements Iterable<ArrayList<String>> {
  private final ArrayList<ArrayList<String>> data = new ArrayList<>();
  private final String subfolderName;
  private String outputDirectory;
  private String fileName;

  public OutputFile(String subfolderName, String... columnNames) {
    this.subfolderName = subfolderName;
    data.add(new ArrayList<>(List.of(columnNames)));
    data.add(new ArrayList<>());
  }

  public void setOutputDirectory(String outputDirectory) {
    this.outputDirectory = outputDirectory;
  }

  public String getOutputPath() {
    return String.format("%s/%s", outputDirectory, subfolderName);
  }

  public void append(String value) {
    data.get(data.size() - 1).add(value);
    if (data.get(data.size() - 1).size() == data.get(0).size()) {
      data.add(new ArrayList<>());
    }
  }

  public void append(int n) {
    append(Integer.toString(n));
  }

  public void append(double n) {
    append(Double.toString(n));
  }

  @Override
  public String toString() {
    return data.toString();
  }

  @Override
  public Iterator<ArrayList<String>> iterator() {
    return data.iterator();
  }

  public String getSubfolderName() {
    return subfolderName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
