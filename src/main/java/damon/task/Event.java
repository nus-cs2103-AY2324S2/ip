package damon.task;

/**
 * Represents an Event task by description, status, start time, and end time of task.
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new Event object with String description parameter,
     * String startTime parameter, and String endTime parameter.
     *
     * @param description Description of Event.
     * @param startTime Start time of Event.
     * @param endTime End time of Event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new Event object with String description parameter,
     * boolean isDone parameter, String startTime parameter, and String endTime parameter.
     *
     * @param description Description of Event.
     * @param isDone Status of Event.
     * @param startTime Start time of Event.
     * @param endTime End time of Event.
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a sentence containing icon, status, description,
     * start time, and end time of Event.
     *
     * @return Sentence representing Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime + " to: " + endTime + ")";
    }
}

