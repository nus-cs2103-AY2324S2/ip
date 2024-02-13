package duke.Task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toFileString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }


    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}