package reacher;

import java.io.Serializable;

public class Task implements Serializable{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }
    public void markNotDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

