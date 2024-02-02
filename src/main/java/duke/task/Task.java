package duke.task;

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

    public void setMark() {
        isDone = true;
    }
    public void setUnMark() {
        isDone = false;
    }
    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    public String toWrite() {
        String s = this.getStatusIcon() + " | " + this.description;
        return s;
    }
}
