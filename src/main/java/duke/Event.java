package duke;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for Event.
     * @param description Description of the event.
     * @param from Starting date and time of the event.
     * @param to Ending date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the starting date and time of the event.
     * @return Starting date and time of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string representation of the event for saving to file.
     * @return String representation of the event for saving to file.
     */
    @Override
    public String toSaveString() {
        return "E | " + (isDone ? "1" : "0") + " | "
                + description + " | " + from + " - " + to;
    }
}
