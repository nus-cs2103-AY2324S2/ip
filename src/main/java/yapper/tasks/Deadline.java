package yapper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description + " (by: " + getFormattedDate() + ")";
    }

    @Override
    protected String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    protected String getFormattedDate(){
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }
}
