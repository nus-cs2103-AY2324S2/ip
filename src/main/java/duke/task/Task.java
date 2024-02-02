package duke.task;

public abstract class Task {
    protected String taskDescription;
    protected boolean isCompleted;

    public Task(String taskDescription, boolean isCompleted) {
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
    }

    public abstract String getTaskDescription();

    public abstract String getStatusIcon();

    public abstract String getTaskIcon();

    protected String trimDescription(String taskDescription) {
        return taskDescription.trim();
    }

    public void markAsComplete() {
        this.isCompleted = true;
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return getTaskIcon() + " | " + getStatusIcon() + " | " + getTaskDescription();
    }
}
