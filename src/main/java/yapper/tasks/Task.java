package yapper.tasks;

public abstract class Task {
    public String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {

        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String getType() {
        return "";
    }
    public String toString() {
        return "[" + getType() + "] " + "[" + getStatusIcon() + "] " + description;
    }
    public abstract String toFileString();
}

