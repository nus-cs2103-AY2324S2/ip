package duke.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private boolean isDone;
    private static final long serialVersionUID = 1L;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
}