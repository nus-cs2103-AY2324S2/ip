public class Task {
    protected String name;
    protected Boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Boolean getDone() {
        return this.isDone;
    }
    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "X":" ") + "] " + this.name;
    }

}
