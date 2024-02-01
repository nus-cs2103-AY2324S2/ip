abstract public class Task {
    private final String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    boolean isDone() {
        return done;
    }

    void mark() {
        this.done = true;
    }

    void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
