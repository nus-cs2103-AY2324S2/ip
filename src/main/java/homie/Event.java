package homie;

/**
 * Event class that extends Task class.
 * Can specify event starts from when and when the events end.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for Event class
     * @param description For the Event Task.
     * @param from This is to keep track when the event starts
     * @param to This is to keep track when the event ends
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
