abstract class Task {
    protected String description;
    protected boolean isDone;

    protected String dateTimeFormat = "MMM d yyyy h:mma";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String toFileString() {
        return String.format("%s,%s", this.getStatusIcon(), this.description);
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}