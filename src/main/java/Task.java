public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    protected String getStatusSymbol() {
        return (this.isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        return " | " + this.getStatusSymbol() + " | " + this.description;
    }

}
