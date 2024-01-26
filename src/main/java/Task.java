abstract public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected static int counter = 0;

    public Task(String description) {
        counter++;
        this.description = description;
        this.isDone = false;
        this.index = counter;
    }
    abstract String getType();

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
}

