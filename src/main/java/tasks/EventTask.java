package tasks;

import datetime.DateTime;
import exceptions.ChaterpillarException;

/**
 * Represents a task which is an event. A <code>tasks.EventTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked,
 * a String representing the start date and/or time of the event, and
 * a String representing the end date and/or time of the event.
 */
public class EventTask extends Task {
    public DateTime dateTimeFrom;
    public DateTime dateTimeTo;
    /**
     * Constructor with event start and end
     * date and/or time specified
     * @param taskname name of task to be tracked
     * @param dateTimeFrom start date and/or time of the event
     * @param dateTimeTo end date and/or time of the event
     */
    public EventTask(String taskname, String dateTimeFrom, String dateTimeTo) throws ChaterpillarException {
        super(taskname);
        this.dateTimeFrom = new DateTime(dateTimeFrom);
        this.dateTimeTo = new DateTime(dateTimeTo);
        this.hasDate = true;
    }

    /**
     * Overloaded Constructor with event start and end
     * date and/or time specified
     * @param taskname name of task to be tracked
     * @param isMarked <code>Boolean</code>
     * @param dateTimeFrom start date and/or time of the event
     * @param dateTimeTo end date and/or time of the event
     */
    public EventTask(String taskname, Boolean isMarked, String dateTimeFrom, String dateTimeTo) throws ChaterpillarException {
        super(taskname,isMarked);
        this.dateTimeFrom = new DateTime(dateTimeFrom);
        this.dateTimeTo = new DateTime(dateTimeTo);
        this.hasDate = true;
        this.isMarked = isMarked;
    }
    @Override
    public Boolean isWithinDate(DateTime dt) {
        return dt.isWithinDate(this.dateTimeFrom, this.dateTimeTo);
    }
    @Override
    public String stringForSaving() {
        return "E|" + super.stringForSaving() + "|"
                + this.dateTimeFrom + "|"
                + this.dateTimeTo;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (from: %s to: %s)", this.dateTimeFrom, this.dateTimeTo);
    }
}
