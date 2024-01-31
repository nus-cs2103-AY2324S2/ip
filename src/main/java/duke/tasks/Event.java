package duke.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an <code>Event</code> with an event name and timespan.
     *
     * @param n Specified event name.
     * @param from Date string from which the event starts.
     * @param to Date string of when the event ends.
     */
    public Event(String n, String from, String to) {
        super(n);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an <code>Event</code> with an event name, timespan and
     * if the event has been completed.
     *
     * @param n Specified event name.
     * @param d If the event is done.
     * @param from Date string from which the event starts.
     * @param to Date string of when the event ends.
     */
    public Event(String n, boolean d, String from, String to) {
        super(n, d);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the date string of when the event starts.
     *
     * @return Event start date.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the date string of when the event ends.
     *
     * @return Event end date.
     */
    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
