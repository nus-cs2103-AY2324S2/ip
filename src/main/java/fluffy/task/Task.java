package fluffy.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected boolean isDone;
    protected String description;
    /**
     * Constructor for Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Task is not done by default
    }

    /**
     * Constructor for Task.
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        // Mark task as done
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        // Mark task as done
        this.isDone = false;
    }

    public String getDescription() {
        // Return task description
        return this.description;
    }

    /**
     * Returns the task in the format to be displayed to the user.
     * @return The task in the format to be displayed to the user.
     */
    @Override
    public String toString() {
        // Return task status icon and description
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns the task in the format to be saved to the file.
     * @return The task in the format to be saved to the file.
     */
    public abstract String toFileString();

    public abstract String getType();

    /**
     * Returns whether the task is done.
     * @return Whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns whether the task is of the same type as the given task.
     * @param task The task to compare with.
     * @return Whether the task is of the same type as the given task.
     */
    public boolean isSameTaskType(Task task) {
        return this.getType().equals(task.getType());
    }
}
