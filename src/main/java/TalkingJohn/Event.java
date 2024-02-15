package talkingjohn;

/**
 * Represents an event task that has a start date and an end date.
 */
public class Event extends Task {
    protected String by1;
    protected String by2;

    /**
     * Constructs an event task with the description, start date, and end date.
     *
     * @param description The description of the event.
     * @param by1         The start date of the event.
     * @param by2         The end date of the event.
     */
    public Event(String description, String by1, String by2) {
        super(description);
        this.by1 = by1;
        this.by2 = by2;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string containing the task type, description, start date, and end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + by1.substring(5) + "to: " + by2.substring(3) + ")";
    }
}
