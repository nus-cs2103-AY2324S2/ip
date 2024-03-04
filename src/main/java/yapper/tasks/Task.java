package yapper.tasks;

/**
 * The Task class represents a generic task with a description and completion status.
 * It serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected boolean isDone;
    private String description;

    /**
     * Constructs a new Task with the given description and initial completion status.
     *
     * @param description The description of the task.
     * @param isDone      The initial completion status of the task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     *
     * @return The status icon ('X' if done, ' ' if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type of the task. Subclasses should override this method.
     *
     * @return The task type.
     */
    public String getType() {
        return "";
    }

    /**
     * Returns a string representation of the task, including type,
     * status icon, and description.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        assert getType() != null : "Task type should not be null";
        return "[" + getType() + "] " + "[" + getStatusIcon() + "] "
                + description;
    }

    /**
     * Abstract method to be implemented by subclasses for converting the task to a string
     * format suitable for saving to a file.
     *
     * @return A formatted string for saving the task to a file.
     */
    public abstract String toFileString();
}

