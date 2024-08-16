package tasks;

/**
 * Represents an event task in a task management application.
 * An event task includes details such as start time and end time along with a basic task description.
 * It extends the Task class by incorporating time-specific information for the event.
 *
 * @author Muhammad Rizki Bayuaji
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event instance with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public String getStart(){
        return this.start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public String getEnd(){
        return this.end;
    }

    /**
     * Returns a string representation of the event, including its type indicator, description,
     * start time, and end time.
     *
     * @return A string representation of the event.
     *
     * @author Muhammad Rizki Bayuaji
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
