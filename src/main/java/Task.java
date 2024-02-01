/**
 * Task class
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Constructor for Task
     * 
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Check if task is done
     * 
     * @return boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    /** 
     * Get task type
     */
    protected String taskType() {
        return "";
    }

    /**
     * Get task status
     * 
     * @return String task status
     */
    public String taskStatus() {
        return this.isDone ? "done" : "not done";
    }

    /**
     * toString method for Task
     * 
     * @return String
     */
    @Override
    public String toString() {
        return taskType() + " | " + taskStatus() + " | " + this.task;
    }
}

/**
 * TaskType enum
 */
enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    HELP,
    DELETE,
    UNKNOWN
}