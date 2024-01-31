package huyang;

import java.time.LocalDateTime;

/**
 * An abstract class representing a task with a name and completion status.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void check() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void uncheck() {
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return The status icon, [X] if done, [ ] if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Converts the task to a formatted string for saving to a file.
     *
     * @return A string in file format representing the task.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, including its status icon and name.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + taskName;
    }
}
