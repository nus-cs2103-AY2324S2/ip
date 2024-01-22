package task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsDoneStatus() {
        return (isDone ? "X" : " ");
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[" + getIsDoneStatus() + "] " + this.description;
    }
}
