public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected enum ID {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public abstract String toString();
}
