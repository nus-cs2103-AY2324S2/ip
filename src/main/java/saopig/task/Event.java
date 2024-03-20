package saopig.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private ZoneId userZoneId = ZoneId.systemDefault();
    private ZonedDateTime startZonedDateTime;
    private ZonedDateTime endZonedDateTime;

    /**
     * Creates an Event task with the given description, start time and end time.
     * The start time and end time are stored as LocalDateTime objects.
     * The user's timezone is obtained from the system.
     *
     * @param description Description of the Event task.
     * @param startTime Start time of the Event task.
     * @param endTime End time of the Event task.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.startZonedDateTime = startTime.atZone(userZoneId);
        this.endZonedDateTime = endTime.atZone(userZoneId);
    }

    /**
     * Returns the start time of the Event task as LocalDateTime object.
     *
     * @return Start time of the Event task as LocalDateTime object.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the Event task as LocalDateTime object.
     *
     * @return End time of the Event task as LocalDateTime object.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }
    /**
     * Changes the start time and end time of the Event task to the new start time and end time.
     *
     * @param newStartTime New start time of the Event task.
     * @param newEndTime New end time of the Event task.
     */
    public void changeTime(LocalDateTime newStartTime, LocalDateTime newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        this.startZonedDateTime = newStartTime.atZone(userZoneId);
        this.endZonedDateTime = newEndTime.atZone(userZoneId);
    }
    /**
     * Changes the start time of the Event task to the new start time.
     *
     * @param newStartTime New start time of the Event task.
     */
    public void changeStartTime(LocalDateTime newStartTime) {
        this.startTime = newStartTime;
        this.startZonedDateTime = newStartTime.atZone(userZoneId);
    }
    /**
     * Changes the end time of the Event task to the new end time.
     *
     * @param newEndTime New end time of the Event task.
     */
    public void changeEndTime(LocalDateTime newEndTime) {
        this.endTime = newEndTime;
        this.endZonedDateTime = newEndTime.atZone(userZoneId);
    }
    /**
     * Returns the type of the task.
     *
     * @return "D" representing the Deadline task.
     */
    @Override
    public String getType() {
        return "E";
    }
    /**
     * Returns a string which displays the Event task in FULL format.
     *
     * @return Event task in FULL format.
     */
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
