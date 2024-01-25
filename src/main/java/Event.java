/**
 * Event class
 * This class contains informations about the event.
 */
public class Event extends Task {
    private String fromTime;
    private String toTime;

    /**
     * Event Constructor
     * @param description
     * @param fromTime
     * @param toTime
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * toString Method
     * @return a string that describe the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.fromTime + "to:" + this.toTime + ")";
    }
}
