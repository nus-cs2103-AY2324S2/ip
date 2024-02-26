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
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;
    }

    /**
     * Gets status of task.
     * 
     * @return String output representing a task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets description of task.
     * 
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets work as done
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Sets work as undone
     */
    public void markTaskAsUndone() {
        isDone = false;
    }

    /**
     * Changes Priority of a task.
     * 
     * @param p Priority of task to be set.
     */
    public void setPriority(int p) {
        this.priority = p;
    }

    /**
     * Returns Priority of a task.
     * 
     * @return The priority change of the Task.
     */
    public Integer getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription() + " " + this.getPriority();
    }
}
