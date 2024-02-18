package cleo;

import java.io.Serializable;

/**
 * Represents a general task in the Duke application.
 * This is an abstract class that other specific types of tasks extend.
 */
public abstract class Task implements Serializable {
    private String description;
    private boolean isDone;
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public abstract String getTypeIcon();

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.getDescription();
    }
}

