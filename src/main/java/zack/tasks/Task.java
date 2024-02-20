package zack.tasks;

import java.time.LocalDateTime;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime addedTime;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      True if the task is marked as done, false otherwise.
     */
    public Task(String description, boolean isDone, LocalDateTime addedTime) {
        assert description != null : "description cannot be null";
        this.description = description;
        this.isDone = isDone;
        this.addedTime = addedTime;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
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
     * Gets a status icon to represent the task's completion status.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string in a specific format for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    public String toFileString() {
        // Basic implementation for Task
        return "T | " + (isDone ? 1 : 0) + " | " + description + " | " + addedTime;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
