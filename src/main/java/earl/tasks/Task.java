package earl.tasks;

/**
 * Abstract class encapsulating a task to be tracked.
 */
public abstract class Task {

    protected TaskType taskType;
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

    /** Returns a visual indicator of completion. */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Returns the description as is. */
    public String getDescription() {
        return description;
    }

    /** Marks as complete. Returns success. */
    public boolean markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    /** Marks as incomplete. Returns success. */
    public boolean markUndone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /** Returns task in storage format. */
    public abstract String toStorageString();
}
