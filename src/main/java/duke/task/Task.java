package duke.task;

/**
 * The `Task` class represents a general task.
 * It provides methods to create strings related to task's details, and edit the status of task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a general task with description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a general task with description and status.
     *
     * @param description The task's description.
     * @param isDone The task's done status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns various fields of the task.
     *
     * @return A string array containing the description and done status of a task.
     */
    public String[] getFields() {
        String[] result = new String[2];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        assert result[1].equals("Y") || result[1].equals("N") : "The isDone will either be 'Y' or 'N'";
        return result;
    }

    /**
     * Returns the formatted string containing done status and description of the task.
     *
     * @return The formatted string containing done status and description of the task.
     */
    public String getDescriptionStatus() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Returns feedback on the task status.
     *
     * @return String representing feedback on the task status.
     */
    public String getMarkStatus() {
        return (isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");
    }

    /**
     * Change the task status as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Change the task status as not done.
     */
    public void unmarkDone() {
        isDone = false;
    }
}
