/**
 * Event class that represents a Task with starting and ending time.
 */
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
        this.type = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Event(String description, String startTime, String endTime, boolean isDone) { // Overloaded constructor: loading from file
        super(description, isDone);
        this.type = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + ", to: " + this.endTime + ")";
    }
    @Override
    public String toStorageFormat() {
        return super.toStorageFormat() + "♢" + this.startTime + "♢" + this.endTime;
    }
}
