package area;

/**
 * Represents a task . A Task object corressponds to a correct command by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private int priority;

    /**
     * Constructs a new task with the given description. The task is not done by
     * default.
     * 
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;
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

    public void setPriority(int p) {
        this.priority = p;
    }

    public Integer getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription() + " " + this.getPriority();
    }
}
