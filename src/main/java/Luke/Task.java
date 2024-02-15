package Luke;

/**
 * Represents a task.
 */
public class Task {
    protected boolean isDone;
    protected String name;

    /**
     * Constructs a task with the given name.
     *
     * @param name the name of the task
     */
    public Task (String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }


    /**
     * Sets the task as done.
     */
    public void setToDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setToNotDone() {
        this.isDone = false;
    }
}