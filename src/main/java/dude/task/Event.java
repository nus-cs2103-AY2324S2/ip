package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates event object.
     * @param description Event description.
     * @param from Event start date.
     * @param to Event end date.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Creates event object.
     * @param description Event description.
     * @param done Whether event is done.
     * @param from Event start date.
     * @param to Event end date.
     */
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
    public boolean isOnDate(LocalDate date) {
        boolean isAfterOrDuringFrom = date.isAfter(this.from) || date.isEqual(this.from);
        boolean isBeforeOrDuringTo = date.isBefore(this.to) || date.isEqual(this.to);
        return isAfterOrDuringFrom && isBeforeOrDuringTo;
    };

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
