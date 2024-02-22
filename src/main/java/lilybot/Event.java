package lilybot;

/**
 * Event class, a subtype of task.
 * Events are specified with a startTime and an endTime.
 */
public class Event extends Task{
    /** Start time of event */
    protected String startTime;
    /** End time of event */
    protected String endTime;

    /**
     * Constructs an Event object.
     *
     * @param description Content of the event entered byDate the user.
     * @param from Start time of the event.
     * @param endTime End time of the event.
     */
    public Event(String description, String from, String endTime) {
        super(description);
        this.startTime = from.trim();
        this.endTime = endTime.trim();
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString() + "("
                + startTime +" "+ endTime.trim() + ")";
    }

    public String getFromTo() {
        return startTime + " "
                + endTime.trim();
    }
}
