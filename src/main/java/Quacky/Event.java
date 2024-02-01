package Quacky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from;
    protected String stringFrom;
    protected LocalDate to;
    protected String stringTo;
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.stringFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.to = to;
        this.stringTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.stringFrom + " to: " + this.stringTo + ")";
    }

    @Override
    protected String toFileString() {
        return "E | " + super.toFileString() + " | " + this.from + " | " + this.to;
    }
}
