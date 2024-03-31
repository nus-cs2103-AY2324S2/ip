package nihao.action.task;

/**
 * Represents a general task.
 */
public class Task {
    protected String taskName;
    private boolean isCompleted;
    public Task(String taskName) {
        this.taskName = taskName;
    }
    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public String getTaskName() {
        return taskName;
    }
    public void mark() {
        isCompleted = true;
    }
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Returns if task name contains a given substring.
     */
    public boolean contains(String str) {
        return taskName.contains(str);
    }

    /**
     * Returns the String representation of the task.
     */
    @Override
    public String toString() {
        String isCompleted = this.isCompleted ? "X" : " ";
        return "[" + isCompleted + "] " + taskName;
    }
}
