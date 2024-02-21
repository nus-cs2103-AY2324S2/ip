package harvard.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, a task with a specific start and end date.
 * Extends the Task class.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event object with the specified description, start date, and end date.
     *
     * @param description the description of the event task
     * @param from        the start date of the event
     * @param to          the end date of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object with the specified description, start date, end date, and completion status.
     *
     * @param description the description of the event task
     * @param from        the start date of the event
     * @param to          the end date of the event
     * @param isDone      the completion status of the event task
     */
    public Event(String description, LocalDate from, LocalDate to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        if (isDone) {
            super.mark();
        }
    }

    /**
     * Generates a string representation of the Event object suitable for saving to a data file.
     *
     * @return a string representing the Event object in a format suitable for saving
     */
    @Override
    public String saveString() {
        return "E," + (super.isDone ? "1," : "0,")
                + super.getDescription() + ","
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ","
                + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Generates a string representation of the Event object suitable for display to the user.
     *
     * @return a string representing the Event object in a format suitable for display
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
