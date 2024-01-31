/**
 * Encapsulates EVENT task. Inherits from Task.
 * @author Tan Qin Yong
 */
public class Event extends Task {

    /** The starting date and time of the event. */
    private String from;

    /** The ending date and time of the event. */
    private String to;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event task.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the type of the task.
     *
     * @return The string "event" representing the type of the task.
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from + " to: " + this.to + ")";
    }
}