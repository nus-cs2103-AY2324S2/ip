package waffles.tasks;

/**
 * The Task class represents a generic task in the waffles chatbot application.
 */
public abstract class Task {

    private static final String TASK_MESSAGE = "[%s] %s";
    private static final String TASK_FILE_TEMPLATE = "%s | %s";
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return The string representation of the task for saving to a file.
     */
    public String toTaskFileTemplate() {
        return String.format(TASK_FILE_TEMPLATE, getStatusIcon(), description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(TASK_MESSAGE, getStatusIcon(), this.description);
    }
}
