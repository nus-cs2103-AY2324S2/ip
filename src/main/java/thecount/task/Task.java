package thecount.task;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

/**
 * Represents a task in the to-do list.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done tasks with X
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Displays a message related to the task.
     *
     * @param currSize The current size of the list.
     */
    public String displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.description, currSize);
        return replyToUser.displayMessage();
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return " ";
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
