package lia;

/**
 * The Event class represents a task that spans a specific time range.
 * It is a subclass of the Task class.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event object with the given description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     * @param isDone      The completion status of the task.
     * @param isImp      The importance of the task.
     */
    public Event(String description, String start, String end, boolean isDone, boolean isImp) {
        super(description, isDone, isImp);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    /**
     * Overrides the toString method to provide a custom string representation of the Event object.
     *
     * @return A formatted string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "]" + "[" + getStatusIcon() + "]" + "[" + getImpIcon() + "] " + getDescription()
                + " (from: " + start + " to: " + end + ")";
    }
}
