package adam.task;

/**
 * {@inheritDoc}
 * An event additionally has two dates for the start and end of the event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Returns an event task.
     *
     * @param description The description of the event.
     * @param from The start of the event.
     * @param to The end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toFileString() {
        return "E," + super.toFileString() + "," + from + "," + to;
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
