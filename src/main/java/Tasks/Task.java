package Tasks;

import java.time.LocalDateTime;

public class Task {
    private String task;
    private String type;
    private boolean isDone;
    private LocalDateTime from;
    private LocalDateTime to;
    private LocalDateTime deadline2;

    public Task(String task, String type, boolean isDone) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
        this.from = null;
        this.to = null;
        this.deadline2 = null;
    }

    public Task(String task, String type, boolean isDone, LocalDateTime from, LocalDateTime to) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
        this.from = from;
        this.to = to;
        this.deadline2 = null;
    }

    public Task(String task, String type, boolean isDone, LocalDateTime deadline2) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
        this.deadline2 = deadline2;
        this.to = null;
        this.from = null;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type;
    }

    public String getTask() {
        return this.task;
    }

    public LocalDateTime getDateTime() {
        return this.deadline2;
    }

    public LocalDateTime getToDateTime() {
        return this.to;
    }

    public LocalDateTime getFromDateTime() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + (this.isDone ? "X" : "") + "] " + this.task;
    }
}
