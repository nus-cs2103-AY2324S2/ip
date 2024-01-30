package cappy.task;

import cappy.storage.CsvFormat;

/**
 * Abstract base class representing a generic task in the task management system.
 *
 * <p>The {@code Task} class provides a common structure for various task types
 * by defining properties such as description and completion status. It also implements
 * the {@link CsvFormat} interface for conversion to CSV (Comma-Separated Values) format.
 *
 * <p><strong>Description:</strong> The description of the task, specifying its details or content.
 * <br><strong>Completion Status:</strong> The status of the task, indicating whether it is done or not.
 */
public abstract class Task implements CsvFormat {
    /**
     * The description for the Task.
     */
    private String description;
    /**
     * The status of the Task, true if it is done.
     */
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.done = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the Task, true if it is done.
     */
    public boolean getDone() {
        return this.done;
    }

    /**
     * Sets the status of the task to be done (complete).
     */
    public void done() {
        this.done = true;
    }

    /**
     * Sets the status of the task to be undone (incomplete).
     */
    public void undone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return this.description.equals(other.description) && this.done == other.done;
    }
}
