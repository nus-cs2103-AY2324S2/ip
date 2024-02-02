package lex.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for the Event class.
     *
     * @param title The title of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(@JsonProperty("title") String title, @JsonProperty("start") LocalDate start,
                 @JsonProperty("end") LocalDate end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Sets the start date of the event.
     *
     * @param start The start date of the event.
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Sets the end date of the event.
     *
     * @param end The end date of the event.
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start.format(DateTimeFormatter.ofPattern(
                "MMM dd yyyy")), end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
