package quacky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Quacky application. An event task is a task
 * that occurs over a period of time, from a start date to an end date.
 */
public class Event extends Task{
    protected LocalDate fromDate;
    protected String stringFromDate;
    protected LocalDate toDate;
    protected String stringTo;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The textual description of the event task.
     * @param fromDate The start date of the event.
     * @param toDate The end date of the event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.stringFromDate = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.toDate = toDate;
        this.stringTo = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.stringFromDate + " to: " + this.stringTo + ")";
    }

    @Override
    protected String toFileString() {
        return "E | " + super.toFileString() + " | " + this.fromDate + " | " + this.toDate;
    }

    @Override
    public boolean clashesWith(Task other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return (this.fromDate.isBefore(otherEvent.toDate) && this.toDate.isAfter(otherEvent.fromDate))
                    || (otherEvent.fromDate.isBefore(this.toDate) && otherEvent.toDate.isAfter(this.fromDate));
        }
        return false;
    }
}
