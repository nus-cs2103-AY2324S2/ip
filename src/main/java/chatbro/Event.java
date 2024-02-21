package chatbro;

/**
 * Event class that represents a chatbro.Task with starting and ending time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructor for Event class.
     *
     * @param description Description of ChatBro.Event object.
     * @param startTime the starting time of the Event object.
     * @param endTime the ending time of the Event object.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        type = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Event(String description, String startTime, String endTime, boolean isDone) { // Overloaded constructor: loading from file
        super(description, isDone);
        type = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + Ui.from() + this.startTime + Ui.to() + this.endTime + ")";
    }
    @Override
    public String toStorageFormat() {
        return super.toStorageFormat() + ";;" + this.startTime + ";;" + this.endTime;
    }
}
