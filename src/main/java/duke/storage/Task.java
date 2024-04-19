package duke.storage;

import duke.parser.Priority;

/**
 * The Task class represents a task in the Duke task manager.
 * It stores information such as the task's description, completion status, and the original command
 * used to create the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String originalCommand;
    protected Priority priority;


    /**
     * Constructs a Task object with the specified original command and description.
     *
     * @param originalCommand The original command used to create the task.
     * @param description     The description of the task.
     */
    public Task(String originalCommand, String description) {
        this.originalCommand = originalCommand;
        this.description = description;
        this.isDone = false;
        this.priority = Priority.NONE;
    }

    /**
     * Returns the status icon of the task ('X' if done, ' ' if not done).
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Gets the original command used to create the task.
     *
     * @return The original command.
     */
    public String getOriginalCommand() {
        return this.originalCommand;
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
     * Retrieves the priority of the task as a string representation.
     *
     * @return the priority of the task as a string ("HIGH!!" for HIGH, "LOW" for LOW, "" for NONE)
     */
    public String getPriority() {
        if (this.priority.equals(Priority.NONE)) {
            return "";
        } else if (this.priority.equals(Priority.HIGH)) {
            return "HIGH!!";
        } else {
            return "LOW";
        }
    }

    /**
     * Sets the priority of the task.
     *
     * @param p the priority to set
     */
    public void setPriority(Priority p) {
        this.priority = p;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] ", getStatusIcon())
                + description
                + " "
                + getPriority();
    }

}
