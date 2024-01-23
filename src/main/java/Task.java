public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatusIcon(boolean status) {
        this.isDone = status;
    }

}