package duke.task;

/**
 * An abstract class representing a task within the Duke application.
 * This class serves as a base for different types of tasks that can be created, tracked, and managed.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description, defaulting the completion status to false (not done).
     *
     * @param description The description of the task, must not be null or empty.
     */
    public Task(String description) {
        validateDescription(description);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task, must not be null or empty.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        validateDescription(description);
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Validates the description ensuring it is not null or empty.
     *
     * @param description The task description.
     */
    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
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
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a status icon for the task based on its completion status.
     *
     * @return "X" if the task is completed, otherwise a space character.
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
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string formatted as [Status] Description, where Status is either "X" or a space.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * This method is intended to be overridden by subclasses to provide specific formatting.
     *
     * @return A formatted string representing the task for file storage.
     */
    public abstract String toFileString();
}
