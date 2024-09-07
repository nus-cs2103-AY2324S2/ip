package ghbot.task;

/**
 * Event Class.
 * This class contains information about the event.
 */
public class Event extends Task {
    private String fromTime;
    private String toTime;

    /**
     * Event Constructor.
     * @param description Description of the event.
     * @param fromTime Start time of the event.
     * @param toTime End time of the event.
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns a string description for the item in the list for file operation.
     * @return A string that describes the item in the list for file operation.
     */
    @Override
    public String toFile() {
        return "E " + super.toFile() + " | " + this.fromTime.trim() + " | " + this.toTime.trim();
    }

    /**
     * Returns a string description of an event.
     * @return A string that describes the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromTime.trim() + " to: " + this.toTime.trim() + ")";
    }
}
