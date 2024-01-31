package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task with status.
     *
     * @param isDone status of task.
     * @param description Description of task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Method to mark or unmark task.
     *
     * @return "X" if marking task, " " if unmarking task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method to set status to marked.
     *
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Method to set status to unmarked.
     *
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Method to return description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to format task for storing in storage file.
     *
     * @return formatted string representation of task for file storage.
     */
    public String toFile() {
        return "";
    }

    /**
     * Method to return string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
