package paimon.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTask();

    public abstract String toFileString();
    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

}