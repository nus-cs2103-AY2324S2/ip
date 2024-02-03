package academicweapon.task;

/**
 * Represents a generic task in the Duke application.
 * The Task class serves as the base class for various types of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description and initialises the isDone flag to false
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the status of the task.
     *
     * @return "X" if the task id done, " " (whitespace) otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting the 'isDone' flag to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting the 'isDone' flag to false.
     */
    public void markAsNotDone() {
        isDone = false;
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
     * Returns a string representation of the task for display purposes.
     *
     * @return String representation of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a formatted string suitable for storing the task in a file.
     *
     * @return Formatted string for storing the task in a file
     */
    public String fileToString() {
        return (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description;
    }
}
