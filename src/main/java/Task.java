public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    protected String getType() {
        return "";
    }
    @Override
    public String toString() {
        return "[" + getType() + "] " + "[" + getStatusIcon() + "] " + description;
    }
}
