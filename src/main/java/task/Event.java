package task;

/**
 * Events: tasks that start at a specific date/time and ends at a specific data/time.
 *
 * @author Titus Chew
 */
public class Event extends Task {

    private final String startDateTime;
    private final String endDateTime;

    /**
     * Constructor for this event.
     *
     * @param name the name of this event
     * @param startDateTime the starting date/time of this event
     * @param endDateTime the ending date/time of this event
     */
    public Event(String name, String startDateTime, String endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets a human-readable description of this task.
     *
     * @return this task in a human-readable string
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startDateTime, endDateTime);
    }
}
