package tony.tasks;

/**
 * The Task class represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected TaskType type;

    /**
     * Creates a new Task with the specified description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Gets the type of the task as a string.
     *
     * @return The type of the task ("TODO," "DEADLINE," or "EVENT").
     */
    public abstract String getType();

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task for display purposes.
     *
     * @return A formatted string representing the task's completion status and description.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns a formatted string representation of the task for storage purposes.
     *
     * @return A formatted string representing the task's completion status and description.
     */
    public String formattedString() {
        return "|" + (isDone ? 1 : 0) + "|" + description;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescription(String input) {
        this.description = input;
    }
}






