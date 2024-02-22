package liv.task;

/**
 * The representation of a task added by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The constructor of the class.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of a task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status of a task.
     * @return The status of the task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Changes the status of a task.
     */
    public void changeStatus() {
        isDone = !isDone;
    }

    /**
     * Gets the icon for the status of a task, [X] for done, [ ] for not done.
     * @return The string representation of the status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns the task in the format that the user will read.
     * @return The String representation of the task.
     */
    public String getDisplayedString() {
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Marks a task as done or completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done or not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the representation of the task that will be stored in the chatbot.
     * @return The inner string representation of the task.
     */
    public String serializeTask() {
        return null;
    }

    /**
     * Checks if a string is in the description of the task.
     * @param other The string to check.
     * @return True if the string other is in the description of task, False otherwise.
     */
    public boolean isInDescription(String other) {
        return this.description.contains(other);
    }
}
