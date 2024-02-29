package SamuelBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that starts and ends at specific times.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a new Event object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startString The start time of the event in the format "yyyy-MM-dd HH:mm".
     * @param endString The end time of the event in the format "yyyy-MM-dd HH:mm".
     */
    public Event(String description, String startString, String endString) {
        super(description);
        this.start = parseDateTime(startString);
        this.end = parseDateTime(endString);
    }
    public Event(String description, String startString, String endString, boolean isDone) {
        super(description);
        this.isDone = isDone;
        this.start = parseDateTime(startString);
        this.end = parseDateTime(endString);
    }

    /**
     * Parses the date and time string into a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to parse.
     * @return The LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task, which is "E" for event.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Converts the event task to a string format for display.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + start.format(formatter) + " - " + end.format(formatter) + ")";
    }

    /**
     * Converts the event task to a string format for saving to a file.
     *
     * @return A string representation of the event task in file format.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, description, start.format(formatter), end.format(formatter));
    }

    /**
     * Gets the description of the event task.
     *
     * @return The description of the event task.
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * Checks if the event task is done.
     *
     * @return true if the event task is done, false otherwise.
     */
    @Override
    public boolean isDone(){
        return isDone;
    }

    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
