package duke;

/**
 * Superclass for all tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructor for Task
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    public void updateDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
