package area;

public class Event extends Task {

    protected String from;

    protected String to;

    /**
     * create an Event object
     * 
     * @param description
     * @param from
     * @param to
     */
    public Event(String description,
            String from,
            String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Return a String representation of Event details
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
