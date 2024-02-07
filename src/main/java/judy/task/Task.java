package judy.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    private String getDecodeStatus() {
        return (isDone() ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String encode() {
        return " | " + getDecodeStatus() + " | " + this.description;
    }

}
