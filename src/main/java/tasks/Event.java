package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that have a start and end date.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates an Event object.
     *
     * @param task Indicates what the task is about.
     * @param start The starting date.
     * @param end The ending date.
     */
    public Event(String task, String start, String end) {
        super(task);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Creates an Event object.
     *
     * @param task Indicates what the task is about.
     * @param start The starting date.
     * @param end The ending date.
     * @param isComplete Indicates whether the task has been completed or not.
     */
    public Event(String task, String dateOfReminder, String start, String end, boolean isComplete) {
        super(task, dateOfReminder, isComplete);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringify() {
        String formattedStartDate = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEndDate = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.stringify() + "(from: " + formattedStartDate + " to: " + formattedEndDate + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + start + " | " + end;
    }

}
