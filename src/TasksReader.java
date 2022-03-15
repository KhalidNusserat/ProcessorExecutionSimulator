import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksReader {

    private static final String[] prefix = {
            "-id",
            "-priority",
            "-creationTime",
            "-requiredTime"
    };


    public static ArrayList<TaskMetadata> readTasksFromFile(String path) {
        ArrayList<TaskMetadata> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String[] data = new String[4];
                for (int i = 0; i < 4; i++) {
                    if (scanner.next().equalsIgnoreCase(prefix[i]))
                        data[i] = scanner.next();
                    else
                        throw new Exception("Expected " + prefix[i]);
                }
                tasks.add(new TaskMetadata(
                        data[0],
                        data[1].equalsIgnoreCase("high")
                            ? TaskPriority.HIGH
                                : TaskPriority.LOW,
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3])
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
