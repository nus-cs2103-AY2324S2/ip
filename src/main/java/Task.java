public class Task {
    protected String taskTitle;
    protected boolean isDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? " ✓" : "";
    }

    @Override
    public String toString() {
        return this.taskTitle + this.getStatusIcon();
    }
}
