package tasks;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getSaveLine() { return "Unknown Task Type";}

    public String getDescription() { return this.description;}
}
