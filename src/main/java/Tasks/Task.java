package Tasks;

abstract public class Task {
    protected String name;
    protected boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    abstract protected void format(String description);

    public String getStatus() {
        return isDone
               ? "[X]"
               : "[ ]";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        return getStatus() + " " + this.name;
    }
    
}
