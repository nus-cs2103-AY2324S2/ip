package lilybot;

public class Event extends Task{
    /** Start time of event */
    protected String from;
    /** End time of event */
    protected String to;

    /**
     * Constructor of event class.
     *
     * @param description Content of the event entered by the user.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString() + "("
                + from +" "+ to.trim() + ")";
    }

    public String getFromTo() {
        return from +" "
                + to.trim();
    }
}
