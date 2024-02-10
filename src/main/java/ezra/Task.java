package ezra;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a formatted string representation of the Task object for storage purposes.
     *
     * @return A formatted string suitable for storage, including the completion status and description.
     */
    public abstract String toStorageString();

    public abstract Task copy();
}
