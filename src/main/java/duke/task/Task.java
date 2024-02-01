package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() { isDone = true; }

    public void markAsUndone() { isDone = false; }

    public abstract void writeTask(String path);

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
