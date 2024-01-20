public class Task {

    protected String name;
    protected boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String x = isDone ? "X" : " ";
        return "[" + x + "] " + this.name;
    }

}
