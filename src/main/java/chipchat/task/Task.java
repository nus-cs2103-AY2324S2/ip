package chipchat.task;

/**
 * Represents a task that can be managed by this chatbot.
 */
public abstract class Task {
    private String description;
    protected boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param description name/description of the task
     * @param isDone completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the name/description of this task matches the query.
     *
     * @param query string that is queried
     * @return result of query
     */
    public boolean matchByString(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String statusIcon = this.isDone ? "X" : " ";
        return String.format("[%s] %s", statusIcon, this.description);
    }

    /**
     * Returns the data-format string of the task. For Chipchat-specific storage purposes.
     *
     * @return a string representation of the task in a parsable format
     */
    public String dataString() {
        return String.format("%s /description %s", this.isDone, this.description);
    }
}
