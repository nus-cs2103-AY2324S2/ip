package dino.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(getStatusIcon() + " | " + description);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(getStatusIcon() + " | " + description);
    }

    @Override
    public String toString() {
        return description;
    }
}


