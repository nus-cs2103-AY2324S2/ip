/**
 * Represents a task which is an event. A <code>EventTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked,
 * a String representing the start date and/or time of the event, and
 * a String representing the end date and/or time of the event.
 */
public class EventTask extends Task {
    public String date_time1;
    public String date_time2;

    /**
     * Basic Constructor
     * @param taskname name of task to be tracked
     */
    public EventTask(String taskname) {
        super(taskname);
        this.date_time1 = "";
        this.date_time2 = "";
    }

    /**
     * Overloaded Constructor with event start and end
     * date and/or time specified
     * @param taskname name of task to be tracked
     * @param date_time_from start date and/or time of the event
     * @param date_time_to end date and/or time of the event
     */
    public EventTask(String taskname, String date_time_from, String date_time_to) {
        super(taskname);
        this.date_time1 = date_time_from;
        this.date_time2 = date_time_to;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(from: %sto: %s)", this.date_time1, this.date_time2);
    }
}
