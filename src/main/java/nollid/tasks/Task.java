package nollid.tasks;

/**
 * Task class represents a generic task with a description and completion status.
 */
public class Task { // Adapted from partial solution provided on CS2103 website
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task is done or not.
     */
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
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The new completion status (true if done, false if not done).
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns boolean value of isDone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Overrides the toString method to provide a string representation of the Task object.
     *
     * @return A formatted string representing the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
