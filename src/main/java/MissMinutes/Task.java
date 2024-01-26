package MissMinutes;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    protected static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy ha");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws MissMinutesException {
        if (this.isDone) {
            throw new MissMinutesException("MissMinutes.Task already marked as done.");
        } else {
            this.isDone = true;
        }
    }

    public void unmark() throws MissMinutesException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new MissMinutesException("MissMinutes.Task already marked as undone.");
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}