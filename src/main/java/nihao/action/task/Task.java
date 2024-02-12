package nihao.action.task;

public class Task {
    private boolean isCompleted;
    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    protected String taskName;
    public String getTaskName() {
        return taskName;
    }
    public Task(String taskName) {
        this.taskName = taskName;
    }
    public void mark() {
        isCompleted = true;
    }
    public void unmark() {
        isCompleted = false;
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
