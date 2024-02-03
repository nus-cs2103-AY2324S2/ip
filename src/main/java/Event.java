package task;

import task.Task;

/**
 * Represents an event with a start and end date.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the given description, start and end date and time.
     *
     * @param description Description of the event.
     * @param from Start date and time.
     * @param to End date and time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
