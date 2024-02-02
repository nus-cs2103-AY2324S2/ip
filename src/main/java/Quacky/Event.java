package Quacky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Quacky application. An event task is a task
 * that occurs over a period of time, from a start date to an end date.
 */
public class Event extends Task{
    protected LocalDate from;
    protected String stringFrom;
    protected LocalDate to;
    protected String stringTo;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The textual description of the event task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
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
