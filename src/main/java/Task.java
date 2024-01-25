public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Method to mark whether task is done or not.
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Mark done.
    public void markDone() {
        isDone = true;
    }

    // Unmark.
    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
