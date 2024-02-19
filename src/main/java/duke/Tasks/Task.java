package duke.Tasks;

/**
 * Represents a generic task.
 */
public abstract class Task {
    String task;

    public boolean isMarked;


    /**
     * Constructs a Task object with the given task description.
     *
     * @param task The description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.isMarked = false;

    }


    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getTask() {
        return task;
    }


    /**
     * Marks the task as done.
     */
    public void markDone() {
        isMarked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        isMarked = false;
    }


    /**
     * Returns a string representing the task's status.
     *
     * @return The task's status string.
     */
    public String mark() {
        return (isMarked ? "[X]" : "[ ]");
    }


    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    public abstract String tag();


    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return mark() + " " + task;
    }
}
