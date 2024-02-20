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
    protected String startTime;

    /**
     * The time when the event ends.
     */
    protected String endTime;

    /**
     * The constructor for an Event object.
     *
     * @param description The description of the Event object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param startTime The time when the event starts.
     * @param endTime The time when the event ends.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the Event object into a human-readable String to be displayed to the user.
     * @return String object that represents the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + startTime + endTime + ")";
    }
}
