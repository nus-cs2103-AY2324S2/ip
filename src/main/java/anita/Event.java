package anita;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The Event class inherits from the Task class.
 * A type of task which can be done by the user.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * The constructor for the Event class.
     * Creates an Event task.
     *
     * @param description The raw user input.
     */
    public Event(String description, String from, String to, String status) {
        super(description, status);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
