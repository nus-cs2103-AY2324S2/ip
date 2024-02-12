package bob.task;

import java.time.LocalDate;

public abstract class Task {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toStorageFormat() {
        return (isDone ? "true" : "false") + " | " + description;
    }

    public boolean isOccurringOn(LocalDate date) {
        return false;
    }

    public boolean isDueIn(int days) {
        return false;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
