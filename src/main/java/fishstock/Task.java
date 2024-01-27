package fishstock;

import java.time.format.DateTimeFormatter;

abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static DateTimeFormatter inDateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    protected static DateTimeFormatter outDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void markAsUndone() {
        this.isDone = false;
    }

    protected abstract String toSaveString();

    protected static int boolToInt(boolean bool) {
        return bool ? 1 : 0;
    }
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}