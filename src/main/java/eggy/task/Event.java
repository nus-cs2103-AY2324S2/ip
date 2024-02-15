package eggy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    /** The start time of the event. */
    private LocalDateTime start;
    /** The end time of the event. */
    private LocalDateTime end;

    /**
     * Constructs an Event.
     *
     * @param name  Name of the Event.
     * @param start Start time of the Event.
     * @param end   End time of the Event.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event.
     *
     * @param name   Name of the Event.
     * @param start  Start time of the Event.
     * @param end    End time of the Event.
     * @param isDone Whether the Event is done.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + " to:"
                + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

    /**
     * Returns the string representation of the Event to be saved in a file.
     *
     * @return String representation of the Event to be saved in a file.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.start + " | " + this.end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.name.equals(event.name) && this.start.equals(event.start)
                    && this.end.equals(event.end);
        }
        return false;
    }
}
