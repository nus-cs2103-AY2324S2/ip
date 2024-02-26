package duke.task;

/**
 * Represents a task the user wants to be reminded of.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param taskName Name of task.
     * @param isDone Completion of task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Retrieves task name.
     *
     * @return Task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks the task as done.
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
     * Returns a string representation of the task with '|' as a splitter between each attribute.
     * String format for data storage.
     *
     * @return String representation of task for data storage.
     */
    public String toFileString() {
        return this.taskName + "|" + this.isDone;
    }

    @Override
    public String toString() {
        return this.isDone
            ? "[X] " + this.taskName
            : "[ ] " + this.taskName;
    }
}
