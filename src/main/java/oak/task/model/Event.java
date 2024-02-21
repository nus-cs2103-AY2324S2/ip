package oak.task.model;

import java.time.LocalDateTime;
import oak.utility.DateTimeUtility;

/**
 * The Event Class to handle all Event-type Tasks
 */
public class Event extends Task {
    /** The icon this task is represented by */
    public static String EVENT_TYPEICON = "E";
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String name, String fromDateTime, String toDateTime) {
        super(name, EVENT_TYPEICON);

        this.fromDateTime = DateTimeUtility.parseStringToLocalDateTime(fromDateTime);
        this.toDateTime = DateTimeUtility.parseStringToLocalDateTime(toDateTime);
    }

    public Event(String name, Boolean isCompleted, String fromDateTime, String toDateTime) {
        super(name, EVENT_TYPEICON);

        if (isCompleted) {
            super.markTaskCompleted();
        }

        this.fromDateTime = DateTimeUtility.parseStringToLocalDateTime(fromDateTime);
        this.toDateTime = DateTimeUtility.parseStringToLocalDateTime(toDateTime);
    }

    public LocalDateTime getToDateTime() {
        return this.toDateTime;
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s|%s|%s",
                EVENT_TYPEICON, super.toTaskListStringFormat(),
                this.fromDateTime.toString(), this.toDateTime.toString());
    }

    @Override
    public String getTypeIcon() {
        return EVENT_TYPEICON;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.toString(),
                DateTimeUtility.parseLocalDateTimeToString(this.fromDateTime),
                DateTimeUtility.parseLocalDateTimeToString(this.toDateTime));
    }
}