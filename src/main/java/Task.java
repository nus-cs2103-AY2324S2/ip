public abstract class Task {
    protected String description;
    private boolean isDone = false;

    @Override
    public String toString() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X": " "; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }

    public abstract String getTaskIcon();
}
