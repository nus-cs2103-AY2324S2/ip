public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.type = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.type = "";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.description);
    }
}