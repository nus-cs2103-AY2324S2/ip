/**
 * Event class stores tasks that have a duration.
 */
public class Event extends Task {
    private String from;
    private String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(from"
                + this.from
                + "to:"
                + this.to
                + ")";
    }
}
