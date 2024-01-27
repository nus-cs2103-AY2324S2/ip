package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've mark this task as done:\n\t\t" + this;
    }

    public String unmarkAsDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n\t\t" + this;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✕"); // mark done task with ✓
    }

    public String textFormattedOutput() {
        return "";
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
