package solaire.data.task;

/**
 * Represents an event task with start and end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates an event task with start and end time.
     *
     * @param taskName description of the event.
     * @param from start time.
     * @param to end time.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public String getStart() {
        return this.from;
    }

    public String getEnd()
    {
        return this.to;
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + "( from: " + from + " to: " + to + ")";
    }

}
