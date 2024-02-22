package teletubbi;

/**
 * Represents an event with start and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new Event object with the specified description, start time and end time.
     *
     * @param description Task description.
     * @param start Start date or time.
     * @param end End date or time.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns Deadline task details in the format to be stored in duke.txt.
     *
     * @return Deadline details in file format.
     */
    public String getFileFormat() {
        return "E|" + this.getStatusIcon() + "|" + this.getDescription() + "|"
                + this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
