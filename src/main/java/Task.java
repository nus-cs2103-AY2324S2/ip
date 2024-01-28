public abstract class Task {
    protected String task;
    protected boolean marked;

    public Task(String task) {
        this.task = task;
        this.marked = false;
    }

    public void markDone() {
        marked = true;
    }

    public void markNotDone() {
        marked = false;
    }

    public String mark() {
        return (marked ? "[X]" : "[ ]");
    }


    public abstract String tag();

    public abstract String toString();
}
