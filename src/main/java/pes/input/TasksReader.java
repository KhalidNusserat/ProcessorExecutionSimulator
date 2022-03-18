package pes.input;

import pes.task.TaskMetadata;
import pes.task.TaskPriority;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class TasksReader {

  private static final String[] prefix = {"id:", "priority:", "creationTime:", "requiredTime:"};

  private final HashSet<Integer> addedID = new HashSet<>();

  public ArrayList<TaskMetadata> readTasksFromFile(String path) throws Exception {
    ArrayList<TaskMetadata> tasks = new ArrayList<>();
    Scanner scanner = new Scanner(new File(path));
    while (scanner.hasNext()) {
      String[] data = new String[4];
      for (int i = 0; i < 4; i++) {
        if (scanner.next().equalsIgnoreCase(prefix[i])) {
          data[i] = scanner.next();
        } else {
          throw new Exception("Expected " + prefix[i]);
        }
      }
      int ID = Integer.parseInt(data[0]);
      if (addedID.contains(ID)) {
        throw new KeyAlreadyExistsException();
      }
      addedID.add(ID);
      tasks.add(
          new TaskMetadata(
              ID,
              data[1].equalsIgnoreCase("high") ? TaskPriority.HIGH : TaskPriority.LOW,
              Integer.parseInt(data[2]),
              Integer.parseInt(data[3])));
    }
    return tasks;
  }
}
