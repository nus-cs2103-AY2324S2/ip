package toothless.tasks;

import java.time.LocalDateTime;

import toothless.Parser;
import toothless.ToothlessException;

/**
 * Represents an event task in the Toothless application. An event task includes a description,
 * start date, and end date.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private static String warningMessage = "Your end date is earlier than your start date :/\n" +
            "Please make sure your end date is after your start date.";

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     * Checks that the start date is before the end date before setting the values.
     * @param description The description of the event.
     * @param startDate The start date of the event in a string format that can be parsed to LocalDateTime.
     * @param endDate The end date of the event in a string format that can be parsed to LocalDateTime.
     * @throws ToothlessException If the end date is before the start date.
     */
    public Event(String description, String startDate, String endDate) throws ToothlessException {
        super.description = description;
        LocalDateTime start = Parser.parseDateTime(startDate);
        LocalDateTime end = Parser.parseDateTime(endDate);
        if (start.isAfter(end)) {
            throw new ToothlessException(warningMessage);
        }
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Constructs an Event task with the specified description, start date, end date, and completion status.
     * Checks that the start date is before the end date before setting the values.
     * @param description The description of the event.
     * @param startDate The start date of the event in a string format that can be parsed to LocalDateTime.
     * @param endDate The end date of the event in a string format that can be parsed to LocalDateTime.
     * @param isDone The completion status of the event.
     * @throws ToothlessException If the end date is before the start date.
     */
    public Event(String description, String startDate, String endDate, boolean isDone) throws ToothlessException {
        super.description = description;
        super.isDone = isDone;
        LocalDateTime start = Parser.parseDateTime(startDate);
        LocalDateTime end = Parser.parseDateTime(endDate);
        if (start.isAfter(end)) {
            throw new ToothlessException(warningMessage);
        }
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + startDate + " | " + endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + super.dateTimeFormat(startDate)
                + " to: " + super.dateTimeFormat(endDate) + ")";
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
