package seiki.data.task;

/**
 * Represents a task.
 */
public class Task {
    protected String taskTitle;
    protected boolean isDone;

    /**
     * Constructor of the Task.
     * @param taskTitle the title of the task.
     */
    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.isDone = false;
    }

    /**
     * Constructor of the Task with status set.
     * @param taskTitle the title of the task.
     * @param isDone    the status of the task.
     */
    public Task(String taskTitle, boolean isDone) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? " ✓" : "";
    }

    @Override
    public String toString() {
        return this.taskTitle + this.getStatusIcon();
    }

    public String toFile() {
        return "| " + (this.isDone ? 1 : 0) + " | " + this.taskTitle;
    }
}
