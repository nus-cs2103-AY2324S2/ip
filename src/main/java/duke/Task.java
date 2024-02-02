package duke;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? " 1 " : " 0 ");
    }

    public void markDone() {
        isDone = true;
    }

    public void mark_not_done() {
        isDone = false;
    }

    @Override
    public String toString() {
        return  "|" + getStatusIcon() + "| " + this.description;
    }

    public String description() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }
}

