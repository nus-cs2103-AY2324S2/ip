package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, boolean done, String by) {
        super(description, done);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getStorageString() {
        return String.format("D | %s | %s", super.getStorageString(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
