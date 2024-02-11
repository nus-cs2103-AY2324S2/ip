package harvard.tasks;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Generates a string representation of the Task object suitable for display to the user.
     *
     * @return a string representing the Task object in a format suitable for display
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return a string representing the completion status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Generates a string representation of the Task object suitable for saving to a data file.
     * This method should be overridden by subclasses.
     *
     * @return a string representing the Task object in a format suitable for saving
     */
    public String saveString() {
        return null; // to be overridden by subclasses
    }
}
