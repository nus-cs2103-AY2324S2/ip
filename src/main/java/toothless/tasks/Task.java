package toothless.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    @Override
    public String toString() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? "X": " "; // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String toWrite() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String dateTimeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public abstract String getTaskIcon();
}
