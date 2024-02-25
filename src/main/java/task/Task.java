package task;

import java.io.Serializable;

/**
 * The Task class represents a basic task in the Roland task management application.
 * It includes information about the task's description and completion status.
 *
 * @author wolffe88
 */

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task (X if done, empty if not done).
     *
     * @return The status icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return The completion status as a boolean.
     */
    public Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Overrides the toString method to provide a formatted representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
