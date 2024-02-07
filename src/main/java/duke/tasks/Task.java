package duke.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private final String description;
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

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
}