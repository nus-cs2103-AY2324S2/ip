package task;

public abstract class Task {
    protected String msg;
    protected boolean done;

    public Task() {
        this.done = false;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    public abstract String toString();
}