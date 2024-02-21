package duke.tasks;

import java.io.Serializable;

/**
 * Represents a task the user wants to record.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected Character taskType;

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     * @param taskType Type of Task.
     */
    public Task(String description, Character taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Get the status icon of the task.
     *
     * @return String A status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get the description of the task.
     *
     * @return String Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the task as not done.
     */
    public void setAsNotDone() {
        isDone = false;
    }

    /**
     * Set the task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Get the type of the task.
     *
     * @return Character Type of the task.
     */
    public Character getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
