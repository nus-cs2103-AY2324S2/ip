package talkingjohn;

/**
 * Represents an event task that has a start date and an end date.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an event task with the description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from         The start date of the event.
     * @param to         The end date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null : "description cannot be null";
        assert from != null : "from cannot be null";
        assert to != null : "to cannot be null";
        this.from = from;
        this.to = to;
    }

    public Event(String description, String notes, String from, String to) {
        super(description, notes);
        assert description != null : "description cannot be null";
        assert notes != null : "notes cannot be null";
        assert from != null : "from cannot be null";
        assert to != null : "to cannot be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string containing the task type, description, start date, and end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + from.substring(5) + "to: " + to.substring(3) + ")";
    }
}
