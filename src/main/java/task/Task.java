package task;

import utils.EnumConverter;

/**
 * Represents a task.
 * <p>
 * This class is used to represent tasks that have a description and a status
 * indicating whether the task is done or not. It provides a constructor to
 * create a new task with a description.
 * </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructs a new {@code Task} instance with the specified description.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.NONE;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     * 
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the priority of the task.
     * 
     * @return The priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority of the task.
     * 
     * @param priority The priority of the task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Removes the priority of the task.
     */
    public void removePriority() {
        this.priority = null;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the priority string representation of the task.
     * 
     * @return The priority string representation of the task.
     */
    public String getPriorityString() {
        if (priority == Priority.NONE) {
            return "";
        }
        return "[" + EnumConverter.convertPriorityToString(priority) + "] ";
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     * 
     * @return The string representation of the task to be saved in the file.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + EnumConverter.convertPriorityToFileString(priority) + " | " + description;
    }

}
