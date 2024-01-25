package helperpackage;

/**
 * Creates an instance of Task.
 */
public class Task {
    /** Task description, also the user input */
    protected String description;
    /** Status of the task, done or not done */
    protected boolean isDone;

    /**
     * Returns an instance of Task, a constructor.
     * 
     * @param description User-defined task name.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
        return getStatusIcon() + description;
    }
}

