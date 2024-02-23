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
     * @param startTime The starting time of the Event object.
     * @param endTime The ending time of the Event object.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        type = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Overloaded constructor for loading from file, with extra 'isDone' parameter.
     * @param description Description of Event object.
     * @param startTime The starting time of the Event object.
     * @param endTime The ending time of the Event object.
     * @param isDone Boolean indicating whether the task is done.
     */
    public Event(String description, String startTime, String endTime, boolean isDone) {
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
