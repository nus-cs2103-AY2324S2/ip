package task;

/**
 * Encapsulates a given task by the user and whether it is done
 *
 *  @author Tan Qin Yong
 */
public class Task {
    /** The description of the task. */
    private String description;

    /** Flag indicating whether the task is done or not. */
    private boolean isDone;

    /**
     * Constructor for creating a new task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task() {
    }

    /**
     * Returns a status icon indicating whether the task is done or not.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets a new description for the task.
     *
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Gets the type of the task.
     *
     * @return The string "task.Task" representing the type of the task.
     */
    public String getType() {
        return "task.Task";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string including the status icon and description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
