package eggy.task;

public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + this.name;
    }
}
