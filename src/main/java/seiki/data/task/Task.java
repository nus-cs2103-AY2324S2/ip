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
        isDone = false;
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
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? " \u2713" : "";
    }

    public boolean hasKeyword(String keyword) {
        return taskTitle.contains(keyword);
    }
    @Override
    public String toString() {
        return this.taskTitle + this.getStatusIcon();
    }

    public String toFile() {
        return "| " + (this.isDone ? 1 : 0) + " | " + this.taskTitle;
    }
}
