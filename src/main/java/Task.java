//public abstract class Task {
//    boolean done;
//    String taskName;
//
//    public Task(String taskName) {
//        this.taskName = taskName;
//        this.done = false;
//    }
//
//    public void mark() {
//        this.done = true;
//    }
//
//    public void unmark() {
//        this.done = false;
//    }
//
//    public abstract String getType();
//
//    @Override
//    public String toString() {
//        return String.format("[%s][%s] %s", getType(), done ? "X" : " ", taskName);
//    }
//}
public class Task {
    private boolean done;
    private String taskName;
    private TaskType taskType;
    String startDate;
    String endDate;

    public Task(String taskName, TaskType taskType, Boolean done ) {
        this.taskName = taskName;
        this.done = done;
        this.taskType = taskType;
    }
    public Task(String taskName, TaskType taskType, Boolean done, String startDate) {
        this.taskName = taskName;
        this.done = done;
        this.taskType = taskType;
        this.startDate= startDate;
    }
    public Task(String taskName, TaskType taskType, Boolean done, String startDate, String endDate) {
        this.taskName = taskName;
        this.done = done;
        this.taskType = taskType;
        this.startDate= startDate;
        this.endDate=endDate;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String status = done ? "[X]" : "[ ]";
        switch (taskType) {
            case T:
                return String.format("[%s]%s %s", taskType, status, taskName);
            case D:
                return String.format("[%s]%s %s (by: %s)", taskType, status, taskName, startDate);
            case E:
                return String.format("[%s]%s %s (from: %s to: %s)", taskType, status, taskName, startDate, endDate);
            default:
                return "";
        }
    }
}