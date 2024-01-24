package Task;

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

    public void setMarked() {
        this.isDone = true;
    }

    public void setUnmarked() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}