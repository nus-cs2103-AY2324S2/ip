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

    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getSaveTask() {
        return (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
