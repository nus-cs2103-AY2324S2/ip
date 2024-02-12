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
     * Returns if task name contains a given substring.
     */
    public boolean contains(String str) {
        return taskName.contains(str);
    }
    @Override
    public String toString() {
        String isCompleted = this.isCompleted ? "X" : " ";
        return "[" + isCompleted + "] " + taskName;
    }
}
