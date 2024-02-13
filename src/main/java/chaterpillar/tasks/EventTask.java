package chaterpillar.tasks;

import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;

/**
 * Represents a task which is an event. A <code>tasks.EventTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked,
 * a String representing the start date and/or time of the event, and
 * a String representing the end date and/or time of the event.
 */
public class EventTask extends Task {
    private final DateTime dateTimeFrom;
    private final DateTime dateTimeTo;
    /**
     * Constructor with event start and end
     * date and/or time specified
     * @param taskName name of task to be tracked
     * @param dateTimeFrom start date and/or time of the event
     * @param dateTimeTo end date and/or time of the event
     */
    public EventTask(String taskName, String dateTimeFrom, String dateTimeTo)
            throws ChaterpillarException {
        super(taskName);
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
    public EventTask(String taskname, Boolean isMarked, String dateTimeFrom, String dateTimeTo)
            throws ChaterpillarException {
        super(taskname,isMarked);
        this.dateTimeFrom = new DateTime(dateTimeFrom);
        this.dateTimeTo = new DateTime(dateTimeTo);
        this.isMarked = isMarked;
        this.hasDate = true;
    }

    /**
     * Checks if the date specified overlaps with the event duration.
     * @param dt date specified.
     * @return <code>true</code> if there is an overlap.
     */
    @Override
    public boolean isWithinDate(DateTime dt) {
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
