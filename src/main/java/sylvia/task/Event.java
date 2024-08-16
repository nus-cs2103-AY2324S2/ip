package sylvia.task;

/**
 * Represents a task with a starting time and an ending time.
 */
public class Event extends Task {
    private TaskDateTime startTime;
    private TaskDateTime endTime;

    /**
     * Creates a new event with the given description, starting time and ending
     * time.
     *
     * @param description The description of the event.
     * @param startTime   The starting time of the event.
     * @param endTime     The ending time of the event.
     * @throws SylviaDateTimeParseException If the starting time or ending time is
     *                                      not in the correct format.
     */
    public Event(String description, String startTime, String endTime) throws SylviaDateTimeParseException {
        super(description);
        this.startTime = new TaskDateTime(startTime);
        this.endTime = new TaskDateTime(endTime);
    }

    /**
     * Creates a new event with the given description, starting time, ending time
     * and done status.
     *
     * @param description The description of the event.
     * @param startTime   The starting time of the event.
     * @param endTime     The ending time of the event.
     * @param isDone      The done status of the event.
     * @throws SylviaDateTimeParseException If the starting time or ending time is
     *                                      not in the correct format.
     */
    public Event(String description, String startTime, String endTime, boolean isDone)
            throws SylviaDateTimeParseException {
        super(description, isDone);
        this.startTime = new TaskDateTime(startTime);
        this.endTime = new TaskDateTime(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime + " - " + endTime + ")";
    }

    /**
     * Returns the serialized string of this task. The serialized string will be
     * stored in the data file.
     *
     * @return The serialized string of the event.
     */
    @Override
    public String serialize() {
        return "E | " + super.serialize() + " | " + startTime.serialize() + " | " + endTime.serialize();
    }
}
