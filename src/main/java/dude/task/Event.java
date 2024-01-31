package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String description, boolean done, String from, String to) {
        super(description, done);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getStorageString() {
        return String.format("E | %s | %s | %s", super.getStorageString(), this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                ")";
    }
}
