public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return (this.isDone ? "X": " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.name;
    }
}
