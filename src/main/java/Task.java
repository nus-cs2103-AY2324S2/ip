public class Task {
    protected String description;
    protected boolean isDone;
    protected String sign;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.sign = "T";
    }
    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }
}


