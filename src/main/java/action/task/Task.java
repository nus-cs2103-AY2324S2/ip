package action.task;

public class Task {
    private boolean isCompleted;
    private String taskName;
    public Task(String taskName) {
        this.taskName = taskName;
    }
    public void mark() {
        isCompleted = true;
    }
    public void unmark() {
        isCompleted = false;
    }
    @Override
    public String toString() {
        String isCompleted = this.isCompleted ? "X" : " ";
        return "[" + isCompleted + "] " + taskName;
    }
}
