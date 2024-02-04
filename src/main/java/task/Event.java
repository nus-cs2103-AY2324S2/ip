package task;
/** This class represents an event which is a task that has start time and end time */
public class Event extends Task{
    /** Field for what time the event starts */
    private String from;
    /** Field for what time the evnet ends */
    private String to;

    /**
     * Constructs an event object
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string text of the task name, when it is from and when it ends and the [E] tag
     *
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
