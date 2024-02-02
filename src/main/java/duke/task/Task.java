package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    private String escapedDescription;

    /**
     * Constructs a new task. By default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.escapedDescription = escapeDescription(description);
        this.isDone = false;
    }

    /**
     * Constructs a new task with the given done status.
     *
     * @param description The description of the task.
     * @param isDone      The done status of the task.
     */
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

    public boolean contains(String keyword) {
        return description.contains(keyword);
    }
}
