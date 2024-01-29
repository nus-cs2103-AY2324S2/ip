package saopig.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private ZoneId userZoneId = ZoneId.systemDefault();
    private ZonedDateTime startZonedDateTime;
    private ZonedDateTime endZonedDateTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.startZonedDateTime = startTime.atZone(userZoneId);
        this.endZonedDateTime = endTime.atZone(userZoneId);
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.ENGLISH);
        return "[E]" + super.toString()
                + " (from: "
                + formatter.format(startZonedDateTime)
                + " to "
                + formatter.format(endZonedDateTime) + ")";
    }
}
