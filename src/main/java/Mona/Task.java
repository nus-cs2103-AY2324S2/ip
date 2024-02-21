package mona;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isCompleted The new completion status of the task.
     */
    public void setCompletion(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    /**
     * Converts the task details into a format suitable for logging.
     *
     * @return The task details in a log-friendly format.
     */
    public String parseToLogRepresentation() {
        return this.toString();
    }

    /**
     * Updates the details of the task with new information.
     *
     * @param newDetails The new details to update the task with.
     */
    public abstract void updateDetails(String newDetails);
}
