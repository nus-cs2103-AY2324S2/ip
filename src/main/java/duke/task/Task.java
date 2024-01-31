package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    private String escapedDescription;

    public Task(String description) {
        this.description = description;
        this.escapedDescription = escapeDescription(description);
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.escapedDescription = escapeDescription(description);
        this.isDone = isDone;
    }

    /**
     * Returns the escaped description of the task.
     *
     * @return The description of the task.
     */
    protected static String escapeDescription(String description) {
        return description.replace("|", "\\|");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + description;
    }

    /**
     * Returns the serialized string of this task. The serialized string will be
     * stored in the data file.
     *
     * @return The serialized string of the task.
     */
    public String serialize() {
        return (isDone ? "1" : "0") + " | " + escapedDescription;
    }
}
