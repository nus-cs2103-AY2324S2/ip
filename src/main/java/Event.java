/**
 * Event class with attributes from and to.
 */
public class Event extends Task {
    /** describe the start period. */
    private String from;
    /** describe the end period. */
    private String to;

    /**
     * Constructor of Event.
     *
     * @param description to describe the event.
     * @param from period when the event start.
     * @param to period when the event end.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor of Event.
     *
     * @param description to describe the event.
     * @param from period when the event start.
     * @param to period when the event end.
     * @param isDone
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * String representation of event.
     *
     * @return string representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * String representation for storage.
     *
     * @return String representation for storage of Event task.
     */
    @Override
    public String toStorageString() {
        return CommandType.EVENT.toString() + " " + super.toStorageString() + " " + this.from + " " + this.to;
    }
}
