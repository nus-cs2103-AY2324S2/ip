package damon.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markBackNotDone() {
        this.isDone = false;
    }

    public boolean isContainKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    //...
}

