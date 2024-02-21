package jmsandiegoo.tyrone.task;

import jmsandiegoo.tyrone.common.Messages;

/**
 * Represents the abstract task item of the application.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * @param description - the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markItem() {
        this.isDone = true;
    }

    /**
     * Un-marks the task as not done.
     */
    public void unmarkItem() {
        this.isDone = false;
    }

    /**
     * Returns boolean whether the task description has the keyword.
     *
     * @param keyword - the keyword to search for.
     * @return boolean - true if it has the keyword, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        String isDoneStr = "[" + (this.isDone ? "X" : " ") + "] ";

        return isDoneStr
                + this.description;
    }

    /**
     * Returns a copy of the particular task object.
     *
     * @return Task.
     */
    public abstract Task copy();

    /**
     * Returns String the encoded format of the task to be stored for file storage.
     *
     * @return String - the encoded string format of the task.
     */
    public String serializeTask() {
        String isDoneStr = this.isDone ? "1" : "0";
        return isDoneStr + " | " + this.description;
    }
}
