public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description ;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getSaveFormat();
}