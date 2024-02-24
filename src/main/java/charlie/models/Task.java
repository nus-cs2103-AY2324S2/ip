package charlie.models;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor for tasks
     * @param description contains information in the form of a string about the task
     */
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

    /**
     * @return the description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return returns the description of task and the status icon of
     * whether or not it has been completed
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
