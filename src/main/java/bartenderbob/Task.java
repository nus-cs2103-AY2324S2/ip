package bartenderbob;
/**
 * Represents a task that has a description and a status.
 */
public class Task {
    /** Description of the task */
    protected String description;
    /** Represents whether the task has been completed */
    protected boolean isDone = false;
    /** Represents the status of the task */
    protected String status;

    /**
     * Creates an instance of a Task class that has a description.
     *
     * @param description Represents the descriptions of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Creates an instance of a Task class that has a description and whether it has been completed.
     *
     * @param description Represents the descriptions of the task.
     * @param isDone Represents whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }
    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    public String show() {
        return "";
    }
    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
