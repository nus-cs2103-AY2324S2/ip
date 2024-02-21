package jojo;

/**
 * Represents an event with a string description, to and from details.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a simplified toString for the ease of saving.
     * @return String
     */
    @Override
    public String simpleToString() {
        return "E " + super.simpleToString() + " | " + from + " - " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
