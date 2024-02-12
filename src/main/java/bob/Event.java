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
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toSavableFormat() {
        return uuid + "|E|" + description + "|" + isDone + "|" + start + "|" + end;
    }

    @Override
    public String getType() {
        return "[E]";
    }
}
