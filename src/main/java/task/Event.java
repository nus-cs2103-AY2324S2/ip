package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class takes in a task string, start time of the event and end time of the event has fields.
 */
public class Event extends Task {
    private static final DateTimeFormatter ORIGINAL_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyyHHmm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor that initialises the task string, start time and end time of the event.
     * @param s Task string of the event
     * @param startTimeString Start time string to be converted to datetime format
     * @param endTimeString End time string to be converted to datetime format
     */
    public Event(String s, String startTimeString, String endTimeString) {
        super(s);

        startTimeString = startTimeString.replaceAll("\\s", "");
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, ORIGINAL_FORMATTER);

        endTimeString = endTimeString.replaceAll("\\s", "");
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, ORIGINAL_FORMATTER);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor that initialises the task string, start time and end time of the event.
     * @param s Task string of the event
     * @param startTime Start time in datetime format
     * @param endTime End time in datetime format
     */
    public Event(String s, LocalDateTime startTime, LocalDateTime endTime) {
        super(s);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean timeEquals(Event event) {
        return event.startTime.equals(this.startTime) && event.endTime.equals(this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.startTime.format(ORIGINAL_FORMATTER),
                this.endTime.format(ORIGINAL_FORMATTER));
    }

    @Override
    public String convertToDataStoreLine() {
        return String.format("E|%s|%s|%s|%s", super.convertToDataStoreLine(),
                super.getTaskString(), this.startTime, this.endTime);
    }

    @Override
    public boolean equals(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            return super.equals(event) && timeEquals(event);
        }
        return false;
    }
}
