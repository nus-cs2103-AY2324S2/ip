public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (this.getStatus() ? "X" : " ");
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}
