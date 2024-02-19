package hal.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

//    public Task(String description) {
//        this.description = description;
//        this.isDone = false;
//    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        String statusWithDesc = String.format("[%s] %s", getStatusIcon(), getDescription());
        return statusWithDesc;
    }

    public abstract String getFileString();

}

