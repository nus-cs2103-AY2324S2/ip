package univus.task;

/**
 * Represents an event task in the Univus application.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new instance of the Event class with the given description, start, and end times.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description.replaceFirst("event ", ""));
        this.start = start.replaceFirst("from", "");
        this.end = end.replaceFirst("to", "");
    }

    /**
     * Returns a string representation of the Event task for display.
     *
     * @return A string containing the formatted representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
