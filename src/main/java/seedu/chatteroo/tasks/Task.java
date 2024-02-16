package seedu.chatteroo.tasks;

/**
 * Represents a task with a description and a status of whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for the Task class.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

        assert description != null : "Task description cannot be null";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotdone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    public String getTaskType() {
        return this.taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }
}
