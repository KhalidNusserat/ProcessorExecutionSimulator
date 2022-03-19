package pes.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputFile implements Iterable<ArrayList<String>> {
  private final ArrayList<ArrayList<String>> data = new ArrayList<>();
  private final String fileType;
  private String fileName;
  private String outputPath;

  public OutputFile(String fileType, String... columnNames) {
    this.fileType = fileType;
    data.add(new ArrayList<>(List.of(columnNames)));
    data.add(new ArrayList<>());
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getOutputPath() {
    return outputPath;
  }

  public void setOutputPath(String outputPath) {
    this.outputPath = outputPath + "/" + fileType + "/" + fileName;
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

  public String getFileType() {
    return fileType;
  }
}
