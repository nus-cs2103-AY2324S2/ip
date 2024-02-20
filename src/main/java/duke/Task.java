package duke;

/**
 * Represents a task.
 */
public class Task {
    protected String description; // Description of the task
    protected boolean isDone; // Indicates if the task is done or not

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, tasks are not done
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ('X' if the task is done, ' ' otherwise).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // 'X' if the task is done, ' ' otherwise
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     *
     * @return The status of the task ("marked" if done, "unmarked" if not done).
     */
    public String markstatus() {
        if (isDone) {
            return "marked";
        } else {
            return "unmarked";
        }
    }

    /**
     * Returns an empty string.
     *
     * @return An empty string.
     */
    public String timeprint() {
        return "";
    }

    /**
     * Returns the type id of the task.
     *
     * @return The type id of the task ("T" for regular task).
     */
    public String typeid() {
        return "T";
    }
}
