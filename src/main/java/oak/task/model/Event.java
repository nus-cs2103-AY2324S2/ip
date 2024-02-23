package oak.task.model;

import java.time.LocalDateTime;

import oak.utility.DateTimeUtility;

/**
 * The Event Class to handle all Event-type Tasks
 */
public class Event extends Task {
    /** The icon this task is represented by */
    public static final String ICON_EVENT = "E";
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructor for Event, for user input
     *
     * @param name
     * @param fromDateTime
     * @param toDateTime
     */
    public Event(String name, String fromDateTime, String toDateTime) {
        super(name, ICON_EVENT);

        this.fromDateTime = DateTimeUtility.parseStringToLocalDateTime(fromDateTime);
        this.toDateTime = DateTimeUtility.parseStringToLocalDateTime(toDateTime);
    }

    /**
     * Constructor for Event, for loading from tasklist.txt
     *
     * @param name
     * @param isCompleted
     * @param fromDateTime
     * @param toDateTime
     */
    public Event(String name, Boolean isCompleted, String fromDateTime, String toDateTime) {
        super(name, ICON_EVENT);

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
                ICON_EVENT, super.toTaskListStringFormat(),
                this.fromDateTime.toString(), this.toDateTime.toString());
    }

    @Override
    public String getTypeIcon() {
        return ICON_EVENT;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.toString(),
                DateTimeUtility.parseLocalDateTimeToString(this.fromDateTime),
                DateTimeUtility.parseLocalDateTimeToString(this.toDateTime));
    }
}
