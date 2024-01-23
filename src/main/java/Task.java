public class Task { //This is basically a ToDo task.
    protected String description;
    protected boolean isDone = false;
    protected String status;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String show() {
        status = isDone? "X": " ";
        return "[T]" + "[" + status + "]" + " " + this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
