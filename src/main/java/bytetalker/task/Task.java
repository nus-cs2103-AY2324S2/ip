package bytetalker.task;

public abstract class Task {
    private String task;
    private boolean isDone;
    private TaskType taskType;
    public Task(TaskType taskType, String task) {
        this.taskType = taskType;
        this.isDone = false;
        this.task = task;
    }

    public Task(TaskType taskType, String task, boolean isDone) {
        this.taskType = taskType;
        this.task = task;
        this.isDone = isDone;
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

    public boolean getStatus() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }
}
