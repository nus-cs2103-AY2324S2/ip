package duke.task;

/**
 * The Task class represents a generic task in the Duke chatbot.
 * It is an abstract class that serves as the base class for specific task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
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
     * @return The status icon, "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
        assert this.isDone : "Task should be marked as done";
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
        assert !this.isDone : "Task should be marked as not done";
    }

    /**
     * Abstract method to generate the formatted content of the task for writing to file.
     *
     * @return The formatted content of the task.
     */
    public abstract String writeContent();

    /**
     * Generates a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
