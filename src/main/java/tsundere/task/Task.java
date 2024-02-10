package tsundere.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes Task with its name.
     * Completion status is false by default.
     *
     * @param description Name of Task.
     */
    public Task(String description) {
        assert description != null : "description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns completion status of Task.
     *
     * @return X if Task is done, blank otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets Task completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets Task completion status to false.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns formatted String for storage purposes.
     *
     * @return formatted saveString.
     */
    public String toSaveString() {
        int x = this.isDone ? 1 : 0;
        return x + "," + this.description;
    }

    /**
     * Returns String representation of Task.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}