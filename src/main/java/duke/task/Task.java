package duke.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public abstract String getType();

    protected String getStatusIcon() {
        return (this.getStatus() ? "X" : " ");
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDetails() { return this.name; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}
