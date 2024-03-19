package seedu.klara.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the <code>Event</code> class to store information
 * about the user-created event e.g., <code>from, to</code>
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for <code>Event</code>.
     * @param description Represents description of description
     * @param from Represents start of event
     * @param to Represents end of event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Represents overridden toString method for printing <code>Event</code> details.
     * @return details of type <code>String</code>
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                                        + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                                        + ")";
    }
}
