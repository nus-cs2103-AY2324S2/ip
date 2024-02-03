package task;

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

    /**
     * Constructs a new {@code Task} instance with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Returns the string representation of the task to be saved in the file.
     * 
     * @return The string representation of the task to be saved in the file.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

}
