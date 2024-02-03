public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String storeData() {
        return this.taskName + "|" + this.isDone;
    }

    @Override
    public String toString() {
        return this.isDone
            ? "[X] " + this.taskName
            : "[ ] " + this.taskName;
    }
}
