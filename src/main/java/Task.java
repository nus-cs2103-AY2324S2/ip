public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
