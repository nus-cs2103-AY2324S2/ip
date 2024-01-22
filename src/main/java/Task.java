public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String str = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return str;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }
}
