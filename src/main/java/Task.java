abstract class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String getTaskType();

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + (isDone ? "[X]" : "[ ]") + " " + description;
    }
}
