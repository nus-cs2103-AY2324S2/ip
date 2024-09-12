package dino.tasks;

import java.io.Serializable;

/**
 * The Task class represents a task with a description and a status of whether it is done or not.
 */
public class Task implements Serializable {
    private final String description;
    private boolean isDone;
    private static final long serialVersionUID = 1L;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The markAsDone() function sets the task to be completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * The markAsUndone function sets task to be incompleted.
     */
    public void markAsUndone() {
        isDone = false;
    }

    public boolean isDone() {
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