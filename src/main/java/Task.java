import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // update task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // update status of task as not done
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}
