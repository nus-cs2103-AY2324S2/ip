package seiki.data.task;

public class Task {
    protected String taskTitle;
    protected boolean isDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        isDone = false;
    }

    public Task(String taskTitle, boolean isDone) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? " ✓" : "";
    }

    public boolean hasKeyword(String keyword) {
        return taskTitle.contains(keyword);
    }
    @Override
    public String toString() {
        return this.taskTitle + this.getStatusIcon();
    }

    public String toFile() {
        return "| " + (this.isDone ? 1 : 0) + " | " + this.taskTitle;
    }
}
