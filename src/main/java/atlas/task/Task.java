package atlas.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int priority; // Assuming 1(highest) to 5(lowest)

    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String str = String.format("[%s] %s (P:%d)", this.getStatusIcon(), this.description, this.priority);
        return str;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }

    public abstract String toFileFormat();
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }
}
