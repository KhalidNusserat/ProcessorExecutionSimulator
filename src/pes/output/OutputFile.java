package pes.output;

import java.util.ArrayList;
import java.util.Iterator;

public class OutputFile implements Iterable<ArrayList<String>> {
    private final String fileType;

    private final ArrayList<ArrayList<String>> data;

    private String path;

    public OutputFile(String fileType, ArrayList<ArrayList<String>> data) {
        this.fileType = fileType;
        this.data = data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getFileType() {
        return fileType;
    }


    @Override
    public Iterator<ArrayList<String>> iterator() {
        return data.iterator();
    }
}
