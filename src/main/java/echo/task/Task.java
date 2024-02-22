/*
 * Package: Echo.Task
 * Module/Project Name: Echo
 * File: Task.java
 *
 * Description:
 * This abstract class represents a generic task with a description and completion status.
 * Specific task types (e.g., Todo, Deadline, Event) will extend this class.
 */

package echo.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Checks if the task is marked as done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Gets the task type code for the specific task type.
     *
     * @return The task type code.
     */
    public abstract String getTaskType();

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return A formatted string representation of the task for file storage.
     */
    public abstract String toFileString();
}
