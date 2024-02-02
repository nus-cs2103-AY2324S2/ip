package duke;

/**
 * Represents a task in the Duke application.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns empty or cross icon depending on whether the task is done.
     *
     * @return A tick or cross.
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
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return Whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the saved task.
     *
     * @return The description of the saved task.
     */
    public String saveData() {
        return "";
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
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
