package bob;

/**
 * A task of type Event.
 */
public class Event extends Task {

    private String start;
    private String end;

    /**
     * Constructor of Event.
     *
     * @param description Description of the event.
     * @param start When the event starts.
     * @param end When the event ends.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String toSavableFormat() {
        return this.uuid + "|E|" + this.description + "|" + this.done + "|" + this.start + "|" + this.end;
    }

    @Override
    public String getType() {
        return "[E]";
    }
}
