package irwyn.tasks;

/**
 * This class encapsulates the Event class.
 * It represents a task event.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Constructor for an Event object.
     *
     * @param description The description of the event.
     * @param start The start of the event.
     * @param end The end of the event.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
