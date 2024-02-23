package lery.task;

/**
 * Represents a Task.
 * The Task class represents a generic task in the Lery chatbot application.
 * It includes properties such as description and completion status.
 *
 * The class provides methods to retrieve the status icon, description,
 * and additional information of the task. It also allows marking the task
 * as done and provides methods to get the task type and extra information.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and sets
     * the completion status to false.
     *
     * @param description the description of the task.
     * @param isDone      the completion status of the task (true if done, false otherwise).
     */
    public Task(String description, boolean isDone) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done or not done and returns a corresponding message.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done or not done and returns a corresponding message.
     *
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Gets the type of the task.
     *
     * @return an empty string (to be overridden by subclasses).
     */
    public String getType() {
        return "";
    }

    /**
     * Gets a shortened version of extra information (to be overridden by subclasses).
     *
     * @return an empty string.
     */
    public String getExtraInfoShortened() {
        return "";
    }

    /**
     * Gets the extra information of the task (to be overridden by subclasses).
     *
     * @return an empty string.
     */
    public String getExtraInfo() {
        return "";
    }

    public boolean getIsDone() {
        return isDone;
    }

}
