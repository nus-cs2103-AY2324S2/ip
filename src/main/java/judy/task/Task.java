package judy.task;

/**
 * The Task class represents a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and initializes it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks whether the task associated with this object has been completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The new completion status of the task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    private String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    /**
     * Gets the encoded status representing whether the task is done or not.
     *
     * @return The encoded status ("1" if done, "0" if not done).
     */
    private String getDecodeStatus() {
        return (isDone() ? "1" : "0");
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
     * Returns a string representation of the Task.
     *
     * @return The formatted string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Encodes the Task as a string for storage.
     *
     * @return The encoded string representation of the Task.
     */
    public String encode() {
        return " | " + getDecodeStatus() + " | " + this.description;
    }

}
