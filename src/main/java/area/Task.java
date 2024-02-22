package area;

/**
 * Represents a task . A Task object corressponds to a correct command by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description. The task is not done by
     * default.
     * 
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status of task.
     * 
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get description of task.
     * 
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set work as done
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Set work as undone
     */
    public void markTaskAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
