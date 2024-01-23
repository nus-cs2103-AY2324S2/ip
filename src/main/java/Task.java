public class Task {
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

    @Override
    public String toString() {
        String toggle = this.isDone ? "[X] " : "[ ] ";
        return toggle + this.name;
    }
}
