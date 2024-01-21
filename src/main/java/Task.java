public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    //Method to mark completed tasks
    public void mark() {
        isDone = true;
    }

    //Method to unmark tasks
    public void unmark() {
        isDone = false;
    }

    //Method to print tasks
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
