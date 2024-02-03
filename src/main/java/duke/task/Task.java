package duke.task;

/**
 * Represents a task that is added by the user.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Initializes a Task with the given description.
     * The isDone field is et to false as the task is obviously not done.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the Task in String format.
     *
     * @return the status icon whic is "X" or " " depending on isDone field.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone field as true;
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the isDone field as false;
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Changes the String representation of the task to displaying its status icon and its description.
     * @return the specified string representation of the task.
     */
    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}
