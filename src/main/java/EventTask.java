/**
 * Represents a task which is an event. A <code>EventTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked,
 * a String representing the start date and/or time of the event, and
 * a String representing the end date and/or time of the event.
 */
public class EventTask extends Task {
    public String dateTimeFrom;
    public String dateTimeTo;

    /**
     * Basic Constructor
     * @param taskname name of task to be tracked
     */
    public EventTask(String taskname) {
        super(taskname);
        this.dateTimeFrom = "";
        this.dateTimeTo = "";
    }

    /**
     * Overloaded Constructor with event start and end
     * date and/or time specified
     * @param taskname name of task to be tracked
     * @param dateTimeFrom start date and/or time of the event
     * @param dateTimeTo end date and/or time of the event
     */
    public EventTask(String taskname, String dateTimeFrom, String dateTimeTo) {
        super(taskname);
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(from: %sto: %s)", this.dateTimeFrom, this.dateTimeTo);
    }
}
