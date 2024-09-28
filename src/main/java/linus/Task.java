package linus;

/**
 * Represents tasks within the app.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an icon to show if the task is completed or not.
     *
     * @return X (yes) or blank (no).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns description of the task in String format.
     *
     * @return String representation of the task's description.
     */
    // instead of toString(), express description as String explicitly
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return Whether the task is done or not.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the String representation of how the task is to be output.
     *
     * @return String representation of how the task is to be output.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
