package task;

/**
 * Represents a Task.
 * A <code>Task</code> object corresponds to a task with a description and a
 * status.
 * e.g., <code>Task read book</code>
 */
public class Task {
    protected final String description;
    protected boolean isDone = false;
    // The taskID is used to identify the task in the database.
    private int taskID;

    /**
     * Constructs a Task object with the specified description.
     * @param description The description of the task.
     */
    public Task(int taskID, String description) {
        this.setTaskID(taskID);
        this.description = description;
    }

    /**
     * Constructs a Task object with the specified description and status.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     */
    public Task(int taskID, String description, boolean isDone) {
        this.setTaskID(taskID);
        this.description = description;
        this.isDone = isDone;
    }

    public String getDetails() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDetails());
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
