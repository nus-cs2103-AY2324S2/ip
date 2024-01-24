/**
 * The Event class represents a task that occurs within a specified time range.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a formatted string representation of the event task".
     *
     * @return The formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }
}
