public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
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

    public String toFile() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
