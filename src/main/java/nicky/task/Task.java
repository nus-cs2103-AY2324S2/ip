package nicky.task;

/**
 * Represents a task in the Nicky application.
 * It includes a description and a boolean flag to indicate whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description and sets it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone flag to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a status icon ("X" for done, " " for not done) to represent the task's completion status.
     *
     * @return The status icon representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, otherwise false.
     */
    public boolean isDone() {
        return this.isDone;
    }


    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}

