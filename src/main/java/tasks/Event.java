package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, inherits from Task.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Event Constructor.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Getter method for start.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Getter method for end.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Formats DateTime correctly.
     *
     * @return A string representing the date.
     */
    public String formatDate(LocalDateTime startEnd) {
        return startEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(this.start) + " to: " + formatDate(this.end) + ")";
    }

    @Override
    public String fileString() {
        return "E " + super.fileString() + " | " + this.start + " to " + this.end;
    }

}
