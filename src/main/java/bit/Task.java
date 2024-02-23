package bit;

/**
 * The parent class for todos, deadlines and events
 */
public class Task {

    protected boolean isDone;
    protected final String description;

    /**
     * Initializes a task.
     *
     * @param description description of task
     */
    public Task(String description) {
        isDone = false;
        this.description = description;

    }

    /**
     * Marks task as complete
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Unchecks task as incomplete.
     */
    public void incomplete() {
        this.isDone = false;
    }

    /**
     * Returns String format of task.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
    public boolean containsKeyword(String key) {
        return description.contains(key);
    }

}
