package arc.tasks;

import arc.exceptions.tasks.EmptyDescriptionException;

/**
 * Represents an abstract Task in the Arc application.
 * This class provides a framework for different types of tasks (e.g., todo, deadline, event)
 * with common properties such as description and completion status.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description. The task is initially not completed.
     *
     * @param description The description of the task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Task(String description) throws EmptyDescriptionException {
        this.setDescription(description);
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Task(String description, boolean isDone) throws EmptyDescriptionException {
        this.setDescription(description);
        this.isDone = isDone;
    }

    /**
     * Serializes the task to a string format for storage.
     * This method must be implemented by subclasses to define how a task is serialized.
     *
     * @return A string representing the serialized form of the task.
     */
    public abstract String serialize();

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done.
     * "X" indicates the task is done, and a space indicates it is not done.
     *
     * @return The status icon as a string.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets the description of the task. If the description is empty, an exception is thrown.
     *
     * @param description The description of the task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    private void setDescription(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
