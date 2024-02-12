package scribbles.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an event which is a task that has a start and end date/time
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new event object with the specified description and start and end date/time.
     *
     * @param description description of event.
     * @param isCompleted true if event is completed.
     * @param start start date/time of event.
     * @param end end date/time of event.
     */
    public Event(String description, boolean isCompleted, LocalDateTime start, LocalDateTime end) {
        super(description, isCompleted);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start date/time in LocalDateTime and returns it in String format.
     *
     * @return Date/time of start of event in String format.
     */
    public String getStartString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.start.format(dateTimeFormat);
    }

    /**
     * Gets the end date/time in LocalDateTime and returns it in String format.
     *
     * @return Date/time of end of event in String format.
     */
    public String getEndString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.end.format(dateTimeFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getStartString() + " to: " + this.getEndString() + ")";
    }
}
