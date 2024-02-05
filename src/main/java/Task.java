public abstract class Task {
    private String task;
    private boolean isDone;
    private TaskType taskType;
    Task(TaskType taskType, String task) {
        this.taskType = taskType;
        this.isDone = false;
        this.task = task;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String toString();

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String getIcon() {
        return this.taskType.getIcon();
    }
}
