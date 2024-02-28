package task;

/**
 * Class for Event object.
 */
public class Event extends Task {
    protected String fromDate;
    protected String toDate;

    /**
     * Constructor for an Event object.
     * @param msg Description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String msg, String from, String to) {
        super(msg);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + fromDate + "to:" + toDate + ")";
    }
}
