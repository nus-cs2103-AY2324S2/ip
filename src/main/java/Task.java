public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    public static int getTaskCount() {
        return taskCount;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
}