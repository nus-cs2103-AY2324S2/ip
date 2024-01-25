public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public abstract String getTypeIcon();

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}

