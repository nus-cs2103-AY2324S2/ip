public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getName() {
        return description;
    }

    public void Mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    //...
}