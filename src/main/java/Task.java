public class Task {
    private String description;
    private boolean isDone = false;
    private String status;

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
        status = isDone? "X": "";
        return "[" + status + "]" + " " + this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
