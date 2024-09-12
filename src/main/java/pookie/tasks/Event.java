package pookie.tasks;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected String start;
    /**
     * The end time of the event.
     */
    protected String end;

    /**
     * Constructor for event task.
     * @param description The description of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event task.
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Returns the string representation of the event task to be written to file.
     * @return The string representation of the event task to be written to file.
     */
    @Override
    public String writeToFileString() {
        return "event " + this.description + " /from " + this.start + " /to " + this.end;
    }
}
