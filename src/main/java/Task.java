public abstract class Task {
    protected boolean isDone = false;
    protected String name;

    Task(String name) {
        this.name = name;
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    abstract String getCommand();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
