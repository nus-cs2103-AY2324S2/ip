package task;

/**
 * Represents a generic task in the task manager application.
 * A task has a description and can be marked as done or undone.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     * Returns "X" if the task is done, otherwise returns a space.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public String getTaskInfo() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String print() {
        String str = "[" + this.getStatusIcon() + "] " + this.description;
        return str;
    }
}
