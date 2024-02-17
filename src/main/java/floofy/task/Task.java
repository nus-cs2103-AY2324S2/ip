package floofy.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

//    public String getTask() {
//        return this.description;
//    }

    public String getType() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String toFileFormat() {
        return String.format("%s | %d | %s", this.getType(), this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
