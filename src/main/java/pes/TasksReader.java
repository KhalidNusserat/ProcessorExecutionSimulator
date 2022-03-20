package pes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import pes.simulation.Task;
import pes.simulation.TaskPriority;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

class TaskInfo {
  private int ID;

  private TaskPriority priority;

  private int creationTime;

  private int requiredTime;

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public void setPriority(TaskPriority priority) {
    this.priority = priority;
  }

  public int getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(int creationTime) {
    this.creationTime = creationTime;
  }

  public int getRequiredTime() {
    return requiredTime;
  }

  public void setRequiredTime(int requiredTime) {
    this.requiredTime = requiredTime;
  }

  @Override
  public String toString() {
    return "TaskInfo{"
        + "ID="
        + ID
        + ", priority="
        + priority
        + ", creationTime="
        + creationTime
        + ", requiredTime="
        + requiredTime
        + '}';
  }
}

public class TasksReader {

  private static final String[] prefix = {"id:", "priority:", "creationTime:", "requiredTime:"};

  private static final HashSet<Integer> addedID = new HashSet<>();

  public static ArrayList<Task> readTasksFromFile(String path) throws IOException {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    TaskInfo[] tasksInfo = mapper.readValue(new File(path), TaskInfo[].class);
    ArrayList<Task> tasks = new ArrayList<>();
    for (TaskInfo taskInfo : tasksInfo) {
      tasks.add(
          new Task(
              taskInfo.getID(),
              taskInfo.getPriority(),
              taskInfo.getRequiredTime(),
              taskInfo.getCreationTime()));
    }
    return tasks;
  }
}
