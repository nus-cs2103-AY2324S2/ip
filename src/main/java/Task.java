public class Task {
    private final String name;
    private final boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    private Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    Task mark() {
        return new Task(this.name, true);
    }

    Task unmark() {
        return new Task(this.name, false);
    }

    String getName() {
        return this.name;
    }

    boolean getDone() {
        return this.done;
    }

    public String toString() {
        return "[" + (this.done?"X":" ") + "] " + this.name;
    }
}
