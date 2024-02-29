package task;

/**
 * Represents a task to be stored in the list of tasks.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Returns an instance of Task. Mark by default as undone.
     *
     * @param taskName User-defined task name.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns an instance of Task. Status of task is provided by the user.
     *
     * @param isDone The status of the task.
     * @param taskName User-defined task name.
     */
    public Task(boolean isDone, String taskName) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns task status
     *
     * @return True, if done. Else, false.
     */
    public boolean checkStatus() {
        return isDone;
    }

    /**
     * Returns the status of the task.
     *
     * @return A string to indicate the status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Sets the status of task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string format of the task for printing to the ui.
     */
    @Override
    public String toString() {
        return getStatusIcon() + taskName;
    }

    /**
     * Returns the string format of the task for writing to save file.
     */
    public String toData() {
        return (isDone ? "1" : "0") + " | " + taskName;
    }

    public boolean hasKeyword(String keyword) {
        return taskName.contains(keyword);
    }
}

