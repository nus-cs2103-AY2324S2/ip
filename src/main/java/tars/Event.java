package tars;

/**
 * Represents Event task
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructor for event class
     * @param description Task name
     * @param from Start date
     * @param to End date
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.printWithStatus() + " (from: " + from + " to: " + to + ")";
    }
}
