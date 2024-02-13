package jayne.task;
/**
 * Represents an event task. In addition to the basic task properties,
 * an event contains a start and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;
    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param start the start time of the event.
     * @param end the end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        assert description != null : "Description should not be null";
        assert start != null && !start.trim().isEmpty() : "Start time should not be null or empty";
        assert end != null && !end.trim().isEmpty() : "End time should not be null or empty";
        this.start = start;
        this.end = end;
    }
    /**
     * Returns the string representation of the event task,
     * including its type, status, description, start time, and end time.
     *
     * @return a string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
    /**
     * Returns the string representation of the event task in a format suitable for file storage.
     * The format includes the task type, status, description, start time, and end time.
     *
     * @return a string representation of the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + "-" + end;
    }

}
