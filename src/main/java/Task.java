public abstract class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String printTask() {
        return String.format("[" + (this.isDone ? "X" : " ") + "] " + this.task);
    }

    public void done() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("%d | %s", (this.isDone ? 1 : 0), this.task);
    }
}
