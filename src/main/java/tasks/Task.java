package tasks;

public abstract class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public void done() {
        this.isDone = true;
    }
    public void undone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }
}
