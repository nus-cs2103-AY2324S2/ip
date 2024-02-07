package judy.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    private String getDateTimeString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + getDateTimeString() + ")";
    }
    @Override
    public String encode() {
        return " D" + super.encode() + " | " + getDateTimeString();
    }
}