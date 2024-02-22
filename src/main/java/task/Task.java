package task;

/**
 * The task.Task class encapsulates a task to be tracked by Howie.
 * It stores information of the task and its status.
 *
 * @author Koo Zhuo Hui
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructor for task.Task.
     *
     * @param s The name of the task.
     */
    public Task(String s) {
        this.task = s;
        isDone = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return Name of task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Retrieves the status of a task.
     *
     * @return Whether task.Task is completed, or not.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Sets task to complete status.
     *
     * @return Returns itself.
     */
    public Task setDone() {
        isDone = true;
        return this;
    }

    /**
     * Set task to undone status.
     *
     * @return Returns itself.
     */
    public Task setUndone() {
        isDone = false;
        return this;
    }

    /**
     * Returns a string representation for saving into file.
     */
    public String encode() {
        String taskType = "T|";
        String taskStatus = (isDone ? 1 : 0) + "|";
        return taskType + taskStatus + task;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task t = (Task) o;
        return this.task.equals(t.task);
    }

    /**
     * Convert this task.Task to a string.
     * @return A string representation of task.Task, including its status.
     */
    @Override
    public String toString() {
        String taskType = "[T][";
        String taskStatus = (isDone ? "X" : " ") + "] ";
        return taskType + taskStatus + task;
    }
}
