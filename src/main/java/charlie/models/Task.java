package charlie.models;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * prints the current status of task
     * @return the status of the task (whether it is completed or not
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * marks a task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * marks a task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * @return whether or not the task has been successfully completed
     */
    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
