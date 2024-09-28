package chad.task;

/**
 * Represents a task that can be done or undone.
 */
public class Task {
    private String name;
    private boolean isDone;

    public Task(String s) {
        this.name = s;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getName() {
        return this.name;
    }

    public void setDone(Boolean b) {
        this.isDone = b;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
