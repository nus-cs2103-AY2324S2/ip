package duke.task;

/**
 * Represents a general task.
 */
public abstract class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param task Description of task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Returns the details of a Task.
     *
     * @return String with description of the Task for display.
     */
    public String printTask() {
        return String.format("[" + (this.isDone ? "X" : " ") + "] " + this.task);
    }

    /**
     * Marks isDone as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks isDone as false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("%d | %s", (this.isDone ? 1 : 0), this.task);
    }

    public String getTask() {
        return this.task;
    }
}
