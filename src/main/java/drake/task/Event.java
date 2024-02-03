package drake.task;

/**
 * Represents an event task with a start and end time.
 * Inherits from the {@code Task} class and includes additional information
 * specific to events, such as the start time and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
