package area;

/**
 * Event is a child class of Task. It is to register tasks that can only be done
 * within a specific timeframe.
 */
public class Event extends Task {

    protected String from;

    protected String to;

    /**
     * create an Event object
     * @param description
     * @param from
     * @param to
     */
    public Event(String description,
            String from,
            String to) {
        super(description);
        this.from = from;
        assert this.from != null : "from cannot be null";
        this.to = to;
        assert this.to != null : "to cannot be null";
    }

    /**
     * Return a String representation of Event details
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
