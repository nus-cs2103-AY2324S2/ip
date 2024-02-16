package bit;

public class Task {

    protected boolean isDone;
    protected final String DESCRIPTION;
    public Task(String description) {
        isDone = false;
        this.DESCRIPTION = description;

    }

    /**
     * Mark task as complete
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Uncheck task as incomplete.
     */
    public void incomplete() {
        this.isDone = false;
    }

    /**
     * Return String format of task.
     * @return task in string format.
     */
    @Override
    public String toString() {
        if(this.isDone) {
            return "[X] " + DESCRIPTION;
        } else {
            return "[ ] " + DESCRIPTION;
        }
    }
    public boolean containsKeyword(String key) {
        return DESCRIPTION.contains(key);
    }

}
