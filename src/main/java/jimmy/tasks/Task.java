package jimmy.tasks;

import java.io.Serializable;

/**
 * Represents a task template.
 */
public abstract class Task implements Serializable {
    protected final String desc;
    private boolean isCompleted;

    /**
     * Constructor for Task class.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName, boolean isCompleted) {
        this.desc = taskName;
        this.isCompleted = isCompleted;
    }

    public abstract String toFileString();

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isCompleted = false;

    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is completed, " " otherwise.
     */
    public String getStatus() {
        return this.isCompleted
                ? "X"
                : " ";
    }

    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.desc;
    }
}
