package martin;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

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
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
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
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task. It is a tick symbol if the task is done, otherwise an empty space.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : " ");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, including the status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task for storing in a file.
     *
     * @return A string representation of the task for storing in a file.
     */
    public abstract String toFileString();
}
