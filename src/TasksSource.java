import java.util.ArrayList;

public class TasksSource {

    private final ArrayList<RunningTask> runningTasks = new ArrayList<>();

    private final Clock clock;


    public TasksSource(Clock clock) {
        this.clock = clock;
    }

    public void createNewTask(TaskMetadata taskInfo) {
        runningTasks.add(new RunningTask(taskInfo));
    }

    public void createNewTasks(ArrayList<TaskMetadata> tasks) {
        for (TaskMetadata taskInfo : tasks)
            createNewTask(taskInfo);
    }

    public boolean isEmpty() {
        return runningTasks.isEmpty();
    }

    public void issue(Scheduler scheduler) {
        ArrayList<RunningTask> toRemove = new ArrayList<>();
        for (RunningTask runningTask : runningTasks) {
            if (runningTask.getMetadata().getCreationTime() == clock.getTime()) {
                scheduler.addTask(runningTask);
                toRemove.add(runningTask);
            }
        }
        runningTasks.removeAll(toRemove);
    }
}
