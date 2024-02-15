package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(
            "[yyyy-MM-dd HH:mm][yyyy/MM/dd HH:mm][yyyy MM dd HH:mm][yyyy.MM.dd HH:mm]");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for Event.
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        if ((start.length() < 10 || (start.length() > 16 && start.length() != 64))
                || (end.length() < 10 || (end.length() > 16 && end.length() != 64))) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (start.length() == 10) {
            start += " 00:00";
        }
        if (end.length() == 10) {
            end += " 00:00";
        }
        this.start = LocalDateTime.parse(start, this.FORMAT);
        this.end = LocalDateTime.parse(end, this.FORMAT);
    }

    /**
     * Constructor for Event.
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     * @param isDone Whether the event is done.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        if ((start.length() < 10 || (start.length() > 16 && start.length() != 64))
                || (end.length() < 10 || (end.length() > 16 && end.length() != 64))) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (start.length() == 10) {
            start += " 00:00";
        }
        if (end.length() == 10) {
            end += " 00:00";
        }
        this.start = LocalDateTime.parse(start, this.FORMAT);
        this.end = LocalDateTime.parse(end, this.FORMAT);
    }

    /**
     * Returns the start date and time of the event.
     * @return The start date and time of the event.
     */
    public String getStart() {
        return this.start.format(this.FORMAT);
    }

    /**
     * Returns the end date and time of the event.
     * @return The end date and time of the event.
     */
    public String getEnd() {
        return this.end.format(this.FORMAT);
    }

    /**
     * Returns the string representation of the event.
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
            + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
            + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
