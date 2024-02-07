public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String printStatus() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;

    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toDataString() {
        return (this.isDone ? "X" : "O") + ":" + this.description;
    }

    @Override
    public String toString() {
        return "[" + printStatus() + "] " + this.description;
    }

}
