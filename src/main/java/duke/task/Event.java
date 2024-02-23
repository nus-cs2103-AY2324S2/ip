package duke.task;

/**
 * Represents an event task.
 * An <code>Event</code> object corresponds to an event task represented by a description, a status, a start time and an end time
 */
public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, int isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string output of the Event task
     * @return the string output of the Event task
     */
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description + " (from: " + this.start + " to: " +
                this.end + ")";
    }
}
