package tsundere.task;

public class Event extends Task {
    protected String from;
    protected String to;

    protected static final String type = "E";

    /**
     * Initializes Event task with its name.
     *
     * @param description Name of Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null : "from should not be null";
        assert to != null : "to should not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns formatted String for storage purposes.
     *
     * @return formatted saveString.
     */
    @Override
    public String toSaveString() {
        return type + "," + super.toSaveString() + "," + from + "," + to + "," + this.getTagString();
    }

    /**
     * Returns String representation of Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
