//Solution adapted from Week 2 A-classes partial solution provided

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
