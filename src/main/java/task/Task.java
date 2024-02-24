package task;

/**
 * The Task class represents a generic task in the EMIS application.
 * It provides methods to manage the status and details of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the specified description and default completion status (false).
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object with the specified completion status and description.
     *
     * @param isDone The completion status of the task.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     *
     * @return The status icon ('X' for completed, ' ' for incomplete).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done and displays a confirmation message.
     * 
     * @return A string confirmation message.
     */
    public String markAsDone() {
        this.isDone = true;
        String response = "Nice! I've marked this task as done:";
        response += ("\n[" + this.getStatusIcon() + "] " + this.description);
        return response;
    }

    /**
     * Marks the task as not done and displays a confirmation message.
     * 
     * @return A string confirmation message.
     */
    public String markAsUndone() {
        this.isDone = false;
        String response = "OK, I've marked this task as not done yet:";
        response += ("\n[" + this.getStatusIcon() + "] " + this.description);
        return response;
    }

    /**
     * Returns a string representation of the Task object for storage purposes.
     *
     * @return A string representing the Task object in the format '{status} | {description}'.
     */
    public String storeString() {
        // done = 1, not done = 0
        int status = isDone ? 1 : 0;
        return status + " | " + this.description;
    }

    /**
     * Returns a string representation of the Task object for display purposes.
     *
     * @return A string representing the Task object in the format '[{status}] {description}'.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
