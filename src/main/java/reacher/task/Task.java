package reacher.task;

import java.io.Serializable;

/**
 * Represents a task.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an undone task with specified description.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * returns status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task undone.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns Task status and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

