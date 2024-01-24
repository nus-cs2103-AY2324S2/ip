public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
        UI.printMarking(this, this.isDone);
    }

    public void unmark() {
        this.isDone = false;
        UI.printMarking(this, this.isDone);
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }
}
