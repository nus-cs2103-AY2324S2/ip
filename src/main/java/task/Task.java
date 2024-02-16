package task;

/**
 * Creates an instance of Task.
 */
public class Task {
    /** Task taskName, also the user input */
    protected String taskName;
    /** Status of the task, done or not done */
    protected boolean isDone;

    /**
     * Returns an instance of Task, a constructor.
     * 
     * @param taskName User-defined task name.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(boolean isDone, String taskName) {
        this.taskName = taskName;
        this.isDone = isDone;
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

    @Override
    public String toString() {
        return getStatusIcon() + taskName;
    }

    public String toData() {
        return (isDone ? "1" : "0") + " | " + taskName;
    }
}

