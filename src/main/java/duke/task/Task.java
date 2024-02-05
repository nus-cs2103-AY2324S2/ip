package duke.task;

/**
 * A task with description
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of a marked or unmarked task
     * @return a string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the boolean value of isDone to true
     */
    public void check() {
        this.isDone = true;
    }

    /**
     * Updates the boolean value of isDone to false
     */
    public void uncheck() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]";
    }
}