package duke.storage;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String originalCommand;

    public Task(String originalCommand, String description) {
        this.originalCommand = originalCommand;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String getOriginalCommand() {
        return this.originalCommand;
    }
    @Override
    public String toString() {
        return String.format("[%s] ", getStatusIcon()) + description;
    }

}
