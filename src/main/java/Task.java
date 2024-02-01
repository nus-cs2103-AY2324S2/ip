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

    public int isDoneNumerical() {
        //to help with saving and loading
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public String saveTask() {
        return String.format("%d|%s", this.isDoneNumerical(), this.description);
    }
}