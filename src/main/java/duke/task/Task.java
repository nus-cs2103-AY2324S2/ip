package duke.task;

/**
 * The Task class represents a generic task in the Duke application.
 * It is an abstract class providing common functionality for all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isComplete;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !isComplete : "Task is already marked as complete";
        isComplete = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        isComplete = false;
    }


    /**
     * Gets the status icon of the task.
     *
     * @return The status icon indicating whether the task is complete or not.
     */
    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    /**
     * Converts the Task object to a format suitable for saving to a file.
     *
     * @return The Task object formatted as a string for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string containing the status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
