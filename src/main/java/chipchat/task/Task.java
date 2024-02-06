package chipchat.task;

import chipchat.action.CommandType;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String statusIcon = this.isDone ? "X" : " ";
        return String.format("[%s] %s", statusIcon, this.description);
    }
}
