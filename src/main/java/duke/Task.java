package duke;

/**
 * Represents an abstract task with a description and a completion status.
 * This class serves as a base for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * Initially, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * By default, it returns the task's description.
     *
     * @return The task's description.
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Returns the status of the task, indicating whether it is done or not.
     * The status is represented by a string: "[X]" for done and "[ ]" for not done.
     *
     * @return The status of the task.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
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
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Abstract method that returns the string representation of the task in a format suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return The string representation of the task in a save format.
     */
    abstract String toSaveFormat();
}
