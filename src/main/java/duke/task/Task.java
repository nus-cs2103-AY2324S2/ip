package duke.task;

/**
 * Represents a general task.
 */
public class Task {
    /**
     * Describes the task that the user has to do.
     */
    protected String description;

    /**
     * Represents whether a task has be completed.
     */
    protected boolean isDone;

    /**
     * Instantiates a task.
     *
     * @param description Describes the task that the user has to do.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Instantiates a todo task with a specific checkmark.
     *
     * @param description Describes the task that the user has to do.
     * @param isDone Represents the boolean describing whether the task is checked.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the string version of a boolean isDone.
     *
     * @return A String that represents if a task has been completed or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string formatting of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Checks the specified task.
     *
     * @return The string of the task that has been completed.
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Unchecks the specified task.
     *
     * @return The string of the task that has been marked as incompleted.
     */
    public String markAsUndone() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Retrieves the string format of a general task.
     *
     * @return The string of the task that is to be stored in local file.
     */
    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return strDone + "|" + this.description;
    }

    /**
     * Updates the description parameter of the task class.
     *
     * @param newDescription
     *
     * @return A string representation of the work done.
     */
    public String updateDescription(String newDescription) {
        String response = "Updated " + this.description + " to " + newDescription;
        this.description = newDescription;
        return response;
    }
}
