package dukio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an event.
 * It is a subclass of Task, with a start and end date.
 */
public class Event extends Task {
    LocalDate start, deadline;

    /**
     * Constructor for the event.
     *
     * @param description The description of the event.
     * @param start       The start date of the event.
     * @param deadline    The end date of the event.
     * @param isDone        The status of the event.
     */
    public Event(String description, LocalDate start, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.deadline = deadline;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s; to: %s)",
                super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
