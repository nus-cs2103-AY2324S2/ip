package saopig.task;

import java.time.LocalDateTime;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the given description.
     * The task is marked as not done by default.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string which displays the status of the Task.
     *
     * @return Status of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string which displays the Task.
     *
     * @return Task in string format.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the done state of the Task.
     *
     * @return Done state of the Task.
     */
    public boolean getIsDoneState() {
        return this.isDone;
    }
    /**
     * Changes the description of the Task to the new description.
     *
     * @param newDescription New description of the Task.
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getType() {
        return null;
    }
}
