public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected static int counter = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.index = counter;
        counter++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
}

