package alastor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromString = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma"));
        String toString = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma"));
        return "[E]" + super.toString() + "(from:" + fromString + " to:" + toString + ")";
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                + " | " + this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            if (super.equals(other)) {
                return this.from.equals(other.from) && this.to.equals(other.to);
            }
        }
        return false;
    }
}
