public abstract class Task {
    protected String task;
    protected boolean marked;

    public Task(String task) {
        this.task = task;
        this.marked = false;

    }

    public String getTask() {
        return task;
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

    @Override
    public String toString() {
        return mark() + " " + task;
    }
}
