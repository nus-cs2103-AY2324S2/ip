package task;

/**
 * Represents an event with a start and end date.
 */
public class Event extends Task {
    private static final String TASK_CODE = "E";
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
        super(TASK_CODE, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the start and end date of the Event object.
     *
     * @return String representation of the start and end date.
     */
    @Override
    public String getDate() {
        return this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + TASK_CODE + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
