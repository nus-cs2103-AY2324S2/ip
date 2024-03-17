package hal.task;

/**
 * The Task class represents a task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ('X' for completed, ' ' for incomplete).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task including its status icon and description.
     */
    public String toString() {
        String statusWithDesc = String.format("[%s] %s", getStatusIcon(), getDescription());
        return statusWithDesc;
    }

    /**
     * Returns a string representing the task suitable for saving to a file.
     *
     * @return A string representing the task for file storage.
     */
    public abstract String getFileString();

}


