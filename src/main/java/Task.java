package main.java;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description);
    }
    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

}