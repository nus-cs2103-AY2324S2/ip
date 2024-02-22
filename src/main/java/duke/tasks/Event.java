package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start and end date and time.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private DateTimeFormatter printFormatter;

    /**
     * Constructs a new Event object with the given description, start time, end time, and print formatter.
     *
     * @param description    The description of the event task.
     * @param start          The start date and time of the event task.
     * @param end            The end date and time of the event task.
     * @param formatter      The formatter used to format the start and end times for printing.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, DateTimeFormatter formatter) {
        super(description);
        this.start = start;
        this.end = end;
        this.printFormatter = formatter;
    }

    /**
     * Constructs a new Event object with the given description, status, start time, end time, and print formatter.
     *
     * @param description    The description of the event task.
     * @param status         The completion status of the event task.
     * @param start          The start date and time of the event task.
     * @param end            The end date and time of the event task.
     * @param formatter      The formatter used to format the start and end times for printing.
     */
    public Event(String description, Boolean status, LocalDateTime start, LocalDateTime end, DateTimeFormatter formatter) {
        super(description, status);
        this.start = start;
        this.end = end;
        this.printFormatter = formatter;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(this.printFormatter)
                + " to: " + this.end.format(this.printFormatter) + ")";
    }

    @Override
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return "E | " + super.convertToFileFormat(storeFormatter) + " | " + this.start.format(storeFormatter)
                + " | " + this.end.format(storeFormatter);
    }

    /**
     * Checks if the specified LocalDateTime is within the range of this task's start and end times,
     * inclusive of the start and end times.
     *
     * @param checkDate The LocalDateTime to check against this task's start and end times.
     * @return {@code true} if the checkDate is equal to the start time or end time of this task,
     *         or if it falls within the range of the start and end times (inclusive), {@code false} otherwise.
     */
    public boolean search(LocalDate checkDate) {
        LocalDate startDate = this.start.toLocalDate();
        LocalDate endDate = this.end.toLocalDate();
        return (checkDate.equals(startDate) || checkDate.equals(endDate))
                || (checkDate.isAfter(startDate) && checkDate.isBefore(endDate));
    }
}
