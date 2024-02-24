package task;

/**
 * The Event class represents an event task in the EMIS application.
 * It extends the Task class and adds additional properties related to event timing.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event object with the specified completion status, description, start time, and end time.
     *
     * @param isDone The completion status of the event.
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event object for storage purposes.
     *
     * @return A string representing the Event object in the format 'E | {status} | {description} | {from} | {to}'.
     */
    @Override
    public String storeString() {
        return "E | " + super.storeString() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the Event object for display purposes.
     *
     * @return A string representing the Event object in the format '[E] {description} (from: {from} to: {to})'.
     */
    @Override
    public String toString() {
        return"[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
