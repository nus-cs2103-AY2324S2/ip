package Oak.task.model;

import Oak.utility.DateTimeUtility;

import java.time.LocalDateTime;

public class Event extends Task {
    public static String typeIcon = "E";
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String name, String fromDateTime, String toDateTime) {
        super(name);

        this.fromDateTime = DateTimeUtility.parseStringToLocalDateTime(fromDateTime);
        this.toDateTime = DateTimeUtility.parseStringToLocalDateTime(toDateTime);
    }

    public Event(String name, Boolean isCompleted, String fromDateTime, String toDateTime) {
        super(name);

        if (isCompleted) {
            super.markTaskCompleted();
        }

        this.fromDateTime = DateTimeUtility.parseStringToLocalDateTime(fromDateTime);
        this.toDateTime = DateTimeUtility.parseStringToLocalDateTime(toDateTime);
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s|%s|%s",
                Event.typeIcon, super.toTaskListStringFormat(),
                this.fromDateTime.toString(), this.toDateTime.toString());
    }

    @Override
    public String getTypeIcon() {
        return Event.typeIcon;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.toString(), DateTimeUtility.parseLocalDateTimeToString(this.fromDateTime), DateTimeUtility.parseLocalDateTimeToString(this.toDateTime));
    }
}