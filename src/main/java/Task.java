public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've mark this task as done:\n\t\t" + this.toString();
    }

    public String unmarkAsDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n\t\t" + this.toString();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
