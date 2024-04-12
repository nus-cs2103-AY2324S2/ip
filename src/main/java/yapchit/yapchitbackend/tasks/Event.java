package yapchit.yapchitbackend.tasks;

/**
 * Event class representing a Event object.
 *
 * Extends Task class
 */
public class Event extends Task {

    private String from;

    private String to;

    /**
     * Constructor of new Event object
     *
     * @param name name of Event
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Prints the Event details onto the screen.
     *
     * @return
     */
    @Override
    public String toString() {
        String tag = super.getDone() ? "[X]" : "[ ]";
        return super.wrapToString(
                "[E]" + tag + " " + super.getName().strip() + " (from: " + this.from + " to: " + this.to + ")");
    }

    /**
     * Getter method which returns the 'from' detail of the event.
     *
     * @return String representing the 'from' detail of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Getter method which returns the 'to' detail of the event.
     *
     * @return String representing the 'to' detail of the deadline.
     */
    public String getTo() {
        return this.to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
