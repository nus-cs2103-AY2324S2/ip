package chatbot;

import java.time.LocalDateTime;

/**
 * Represents an event with a start time and an end time.
 */
public class Event extends chatbot.Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs an event.
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        String formattedStartTime = startTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String formattedEndTime = endTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedStartTime, formattedEndTime);
    }

    /**
     * Checks if the event starts at the specified date.
     * @param date The date to check against.
     * @return True if the event starts at the specified date, false otherwise.
     */
    public Boolean isStartTime(LocalDateTime date) {
        return this.startTime.equals(date);
    }

    /**
     * Checks if the event ends at the specified date.
     * @param date The date to check against.
     * @return True if the event ends at the specified date, false otherwise.
     */
    public Boolean isEndTime(LocalDateTime date) {
        return this.endTime.equals(date);
    }
}
