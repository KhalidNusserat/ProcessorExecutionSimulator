public class TaskMetadata {

    private final String ID;

    private final TaskPriority priority;

    private final int creationTime;

    private final int requiredTime;


    public TaskMetadata(String id, TaskPriority priority, int creationTime, int requiredTime) {
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

    @Override
    public String toString() {
        return "TaskInfo{" +
                "ID='" + ID + '\'' +
                ", priority=" + priority +
                ", creationTime=" + creationTime +
                ", requiredTime=" + requiredTime +
                '}';
    }
}