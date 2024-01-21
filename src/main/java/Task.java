public abstract class Task {
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

    @Override
    public String toString() {
        String checkbox = "[" + (this.done ? "x" : " ") + "] ";
        return checkbox + this.name;
    }
}
