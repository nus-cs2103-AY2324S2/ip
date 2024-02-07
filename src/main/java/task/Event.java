package task;

/**
 * Class for Event object.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for an Event object.
     * @param msg Description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String msg, String from, String to) {
        super(msg);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + "to:" + to + ")";
    }
}
