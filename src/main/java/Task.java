package main.java;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTask();
    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

}