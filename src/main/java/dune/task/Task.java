package dune.task;

public abstract class Task {

    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public void complete() {
        this.isDone = true;
    }

    public void incomplete() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String done = this.isDone ? "[X]": "[ ]";
        return done + " " + this.description;
    }
}
