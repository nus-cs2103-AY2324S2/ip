package task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsDoneStatus() {
        return (isDone ? "O" : "X");
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    public String outputString() {
        return getIsDoneStatus() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getIsDoneStatus() + "] " + this.description;
    }
}
