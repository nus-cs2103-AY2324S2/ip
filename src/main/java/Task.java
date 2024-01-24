public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), description);
    }

    public void markDone() {
        isDone = true;
    }
    public void unmarkDone() {
        isDone = false;
    }
}