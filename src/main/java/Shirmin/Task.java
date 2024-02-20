package Shirmin;

public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    //...
    public Integer isDone() {
        return this.isDone
                ? 1
                : 0;
    }
}