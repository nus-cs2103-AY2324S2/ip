package duke.tasklist;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object with the given description and completion status.
     *
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Returns the status icon of the task.
     * The status icon represents the completion status of the task.
     * If the task is marked as done, the status icon will be "X".
     * Otherwise, the status icon will be a space character.
     *
     * @return the status icon of the task ("X" if done, " " if not done)
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Retrieves the status of the task.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task to the true / false.
     *
     * @param status the completion status of the task (true indicates completed, false indicates incomplete).
     *
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }


    /**
     * Retrieves the name of the Task.
     *
     * @return the name of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the Task object.
     * The string representation includes the status icon
     * and the description of the task.
     *
     * @return a string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Allows saving of the Task object in a String format so that it can be stored in the database.
     *
     * @return String interpretation of Task object.
     */
    public String toStorageString() {
        return "T | " + this.getStatus() + " | " + this.description;
    }

}
