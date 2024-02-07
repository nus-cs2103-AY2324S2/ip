package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

    public abstract String toSave();
}
