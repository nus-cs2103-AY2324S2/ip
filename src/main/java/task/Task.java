package task;

/**
 * The task.Task class encapsulates a task to be tracked by Howie.
 * It stores information of the task and its status.
 * @author Koo Zhuo Hui
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructor for task.Task.
     * @param s The name of the task.
     */
    public Task(String s) {
        this.task = s;
        isDone = false;
    }

    /**
     * Retrieves the name of the task.
     * @return Name of task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Retrieves the status of a task.
     * @return Whether task.Task is completed, or not.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Set task.Task to complete status.
     * @return Returns itself.
     */
    public Task setDone() {
        isDone = true;
        return this;
    }

    /**
     * Set task.Task to undone status.
     * @return Returns itself.
     */
    public Task setUndone() {
        isDone = false;
        return this;
    }

    public String encode() {
        String s = "T|" + (isDone ? 1 : 0) + "|" + task;
        return s;
    }

    /**
     * Convert this task.Task to a string.
     * @return A string representation of task.Task, including its status.
     */
    @Override
    public String toString() {
        return "[T][" +(isDone ? "X" : " ") + "] " + task;
    }
}
