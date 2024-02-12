package task;

/**
 * Represents a task.
 */
public class Task {
    protected String taskCode;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon in String type based on isDone.
     *
     * @return Status icon of isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status icon in Integer type based on isDone.
     *
     * @return Status integer of isDone.
     */
    public int getStatusInt() {
        return (isDone ? 1 : 0);
    }

    /**
     * Returns the task code of the specified task.
     *
     * @return Task code.
     */
    public String getTaskCode() {
        return this.taskCode;
    }

    /**
     * Returns the description of the specified task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the due date / start date / end date of the specified task.
     * By default, date is an empty string.
     *
     * @return Task due date / start date / end date.
     */
    public String getDate() {
        return "";
    }

    /**
     * Sets the task to be marked as completed.
     */
    public void setMarked() {
        this.isDone = true;
    }

    /**
     * Sets the task to be unmarked as incomplete.
     */
    public void setUnmarked() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
