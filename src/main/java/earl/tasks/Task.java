package earl.tasks;

/**
 * Abstract class encapsulating a task to be tracked.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     *
     * @param description  a {@code String} naming the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Class constructor specifying the current completion status.
     *
     * @param status       a {@code String} that is either "X" or " "
     * @param description  a {@code String} naming the task
     */
    public Task(String status, String description) {
        this.description = description;
        isDone = status.equals("X");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    public boolean markUndone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.getStatusIcon(), description);
    }

    /** Returns task in storage format. */
    public abstract String toStorageString();
}
