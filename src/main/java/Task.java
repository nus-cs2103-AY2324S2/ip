public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    // to return string representation of task for file writing
    public String toFileString() {
        return ""; // Override in subclasses
    }
}
