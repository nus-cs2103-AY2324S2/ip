package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates EVENT task. Inherits from task.Task.
 * @author Tan Qin Yong
 */
public class Event extends Task {

    /** The starting date and time of the event. */
    private LocalDate from;

    /** The ending date and time of the event. */
    private LocalDate to;

    /**
     * Constructs an task.Event object.
     *
     * @param description The description of the event task.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    /**
     * Gets the type of the task.
     *
     * @return The string "event" representing the type of the task.
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Returns a string representation of the task.Event object.
     *
     * @return A string representation of the task.Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = this.from.format(formatter);
        String formattedTo = this.to.format(formatter);
        return "[E]" + super.toString()
                + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
