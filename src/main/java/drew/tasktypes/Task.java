package drew.tasktypes;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString(){
        return description;
    }

    public String toStatusString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toSaveFormatString();

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }
}
