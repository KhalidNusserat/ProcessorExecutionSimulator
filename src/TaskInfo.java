public class TaskInfo {

    private final String ID;

    private final TaskPriority priority;

    private final int creationTime;

    private final int requiredTime;


    public TaskInfo(String id, TaskPriority priority, int creationTime, int requiredTime) {
        ID = id;
        this.priority = priority;
        this.creationTime = creationTime;
        this.requiredTime = requiredTime;
    }

    public String getID() {
        return ID;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getRequiredTime() {
        return requiredTime;
    }
}
