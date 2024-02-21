package jmsandiegoo.tyrone.task;

import jmsandiegoo.tyrone.common.DateTime;

/**
 * Represents the event task of the application.
 */
public class Event extends Task {
    private final DateTime startDateTime;
    private final DateTime endDateTime;

    /**
     * @param description - the description of the task.
     * @param startDateTime - the from / start datetime of the event task.
     * @param endDateTime - the to / end datetime of the event task.
     */
    public Event(String description, DateTime startDateTime, DateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        String taskTypeStr = "[E] ";
        String formDateTimeStr = " (from: " + this.startDateTime.formatDateTime();
        String toDateTimeStr = " to: " + this.endDateTime.formatDateTime() +")";
        return taskTypeStr
                + super.toString()
                + formDateTimeStr
                + toDateTimeStr;
    }

    @Override
    public Task copy() {
        Event eventCopy = new Event(
                super.description,
                this.startDateTime,
                this.endDateTime
        );
        eventCopy.isDone = this.isDone;

        return eventCopy;
    }

    @Override
    public String serializeTask() {
        String taskSerializedStr = "E | "
                + super.serializeTask()
                + " | "
                + this.startDateTime.serializeDateTime()
                + " - "
                + this.endDateTime.serializeDateTime();;

        return taskSerializedStr;

    }
}
