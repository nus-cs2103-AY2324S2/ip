package bartenderbob;
public class Task {
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
        return "";
    }
}
