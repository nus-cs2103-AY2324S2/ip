package duke;

public class Task {
    private String description;
    private boolean done;

    /**
     * Constructor for Task class.
     *
     * @param description The description of the task.
     * @param done        The status of the task.
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status of the task.
     *
     * @return The status of the task.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the status of the task.
     *
     * @param done The status of the task.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the string representation of the task's status.
     * 
     * @return A string saying if a task is done or not.
     */
    public String getDoneMarker() {
        return done ? "[X]" : "[]";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", getDoneMarker(), description);
    }
}