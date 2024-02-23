package cvb.tasks;

/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for more specialized task types.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} instance with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructs a new {@code Task} instance with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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

    /**
     * Converts the {@code Task} object to a string format suitable for saving to a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    public String toFile() {
        return (this.isDone ? "1" : "0") + " | " + this.getDescription();
    }

    /**
     * Returns a string representation of the {@code Task} object.
     *
     * @return A formatted string representing the task for display.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
