package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private boolean isDone;
    private String desc;

    public Task(String desc) {
        this.isDone = false;
        this.desc = desc;
    }
    public Task(String desc, String isDone) {
        this.desc = desc;
        if (isDone.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public static String toStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public boolean isDone() {
        return isDone;
    }
    public String getDesc() {
        return this.desc;
    }

    public abstract String getStatusIcon();

    public void maskAsDone() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public abstract String toString();
    public abstract String save();
}
