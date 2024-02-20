package floofy.task;

/**
 * Represents a task.
 */
public class Task {
    /** The description of the task */
    protected String description;
    /** The status of the task */
    protected boolean isDone;

    /**
     * Constructs a new object of the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return "";
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done. Updates isDone to true.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone. Updates isDone to false.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the String of a task in its file format.
     */
    public String toFileFormat() {
        return String.format("%s | %d | %s", this.getType(), this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the String form of a task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
