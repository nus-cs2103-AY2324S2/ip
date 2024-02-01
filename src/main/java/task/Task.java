package task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone = false;
    public static final DateTimeFormatter DATE_TIME_STRING_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String writeTask() {
        return isDone + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
