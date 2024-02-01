public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFileFormat() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }
}
