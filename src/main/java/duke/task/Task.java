package duke.task;

/**
 * Represents a task with a description and a completion status.
 * This class provides methods to retrieve information about the task
 * to either be read or save to the hard disk.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * Sets the default isDone value to false.
     *
     * @param description a String for the task's description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task's details as a string.
     *
     * @return a String displaying if the task is completed and its description
     */
    public String toString() {
        return (isDone ? "[X] " + description : "[ ] " + description); //mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string containing the task's data to be saved to the hard disk.
     *
     * @return a String containing the task's data formatted to be saved onto the hard disk
     */
    public String getData() {
        return (isDone ? "1 | " + description : "0 | " + description);
    }
}
