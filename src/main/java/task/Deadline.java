package task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String writeTask() {
        return "D | " + super.writeTask() + " | "
                + by.format(DATE_TIME_STRING_FORMAT);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + by.format(DATE_TIME_STRING_FORMAT) + ")";
    }
}
