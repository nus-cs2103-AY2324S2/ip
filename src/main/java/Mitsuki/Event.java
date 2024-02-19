package Mitsuki;

/**
 * Contains information for the creation of Event objects.
 *
 * @author Tee Chu Jie
 */
public class Event extends Task {

    /**
     * The time when the event starts.
     */
    protected String from;

    /**
     * The time when the event ends.
     */
    protected String to;

    /**
     * The constructor for an Event object.
     *
     * @param description The description of the Event object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param from The time when the event starts.
     * @param to The time when the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event object into a human-readable String to be displayed to the user.
     * @return String object that represents the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + to + ")";
    }
}
