package shon.task;

abstract public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    public String markAsNotDone() {
        this.isDone = false;
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String formatTask() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    public abstract String formatData();
}
