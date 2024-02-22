package task;

/**
 * Represents a task that needs to be done.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task with the given name and sets its completion status to
     * false.
     *
     * @param name The name of the task.
     */
    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Constructs a Task with the given name and completion status.
     *
     * @param name   The name of the task.
     * @param isDone The completion status of the task.
     */
    Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Marks the task as Done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[ ] %s", this.name);
        }
    }
}