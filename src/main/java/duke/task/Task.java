package duke.task;

/**
 * Represents a task the user wants to be reminded of.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructor of task object.
     *
     * @param taskName Task name.
     * @param isDone Is completed task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task with '|' as a splitter between each attribute.
     * String format for data storage.
     *
     * @return String representation of task for data storage.
     */
    public String storeData() {
        return this.taskName + "|" + this.isDone;
    }

    @Override
    public String toString() {
        return this.isDone
            ? "[X] " + this.taskName
            : "[ ] " + this.taskName;
    }
}
