package chimp.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that extends the Task class.
 * An event task has a start date and an end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event object with the specified text, status, start date, and end date.
     *
     * @param text   the description of the event task
     * @param status the status of the event task
     * @param from   the start date of the event task
     * @param to     the end date of the event task
     */
    public Event(String text, TaskStatus status, LocalDate from, LocalDate to) {
        super(text, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
