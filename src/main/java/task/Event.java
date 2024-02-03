package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an event.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, boolean completed, LocalDateTime start, LocalDateTime end) {
        super(description, completed);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
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

    public void setStart(LocalDateTime date) {
        this.start = date;
    }

    public void setEnd(LocalDateTime date) {
        this.end = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getStartString() + " to: " + this.getEndString() + ")";
    }
}
