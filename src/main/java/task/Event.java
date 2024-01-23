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
     * Constructor for an event.
     * @param name The name of the event.
     * @param startDateTime The starting date/time of the event.
     * @param endDateTime The ending date/time of the event.
     */
    public Event(String name, String startDateTime, String endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets a human-readable description of the task.
     * @return The task in a human-readable string.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startDateTime, endDateTime);
    }
}
