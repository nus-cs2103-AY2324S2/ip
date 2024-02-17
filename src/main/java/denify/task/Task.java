package denify.task;

/**
 * Represents a generic task in Denify.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a `Task` with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Sets the task's done status.
     *
     * @param done True if the task is done, false otherwise.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }
    /**
     * Retrieves the status icon for the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Retrieves the status symbol for the task.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    private String getStatusSymbol() {
        return (this.isDone ? "1" : "0");
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
     * Returns a string representation of the task for display.
     *
     * @return A formatted string with task status and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * Returns a string representation of the task for storage in a file.
     *
     * @return A formatted string with task status and description for file storage.
     */
    public String toFileString() {
        return " | " + this.getStatusSymbol() + " | " + this.description;
    }
}
