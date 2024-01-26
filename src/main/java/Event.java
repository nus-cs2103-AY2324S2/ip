public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructor for Event class.
     *
     * @param description Description of Event object.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.type = 'E';
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + ", to: " + this.endTime + ")";
    }
}
