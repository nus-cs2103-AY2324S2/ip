package bit;

/**
 * A task with a start and end time
 */
public class Event extends Task {

    private final String start;
    private final String end;

    /**
     * Initialise an event
     * @param description the description of an task
     * @param start a string that gives start of event
     * @param end a string that gives end of event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + DESCRIPTION + " (from: " + start + " to: " + end + ")";
        } else {
            return "[E][ ] " + DESCRIPTION + " (from: " + start + " to: " + end + ")";
        }
    }

    /**
     * Use to get start time of event
     * @return start time
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Use to get end time of event
     * @return end time
     */
    public String getEnd() {
        return this.end;
    }
}
