package kai;

import java.io.Serializable;

/**
 * Represents an event task.
 * Extends the base Task class and includes additional information about the event.
 */

public class Event extends Task implements Serializable {

    /**
     * The start time of the event
     */
    protected String from;

    /**
     * The end time of the event.
     */
    protected String to;


    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + " )";
    }
}
