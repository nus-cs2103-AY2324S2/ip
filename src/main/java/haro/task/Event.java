package haro.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents an event task in the application, extending the Task class.
 * It provides specific functionality for handling event tasks with start and end dates.
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an Event instance with the specified task description, start date, and end date.
     *
     * @param task Task description
     * @param start Start date of the event
     * @param end   End date of the event
     */
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;

        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }
    }

    /**
     * Constructs an Event instance with the specified task description, start date, end date, and done status.
     *
     * @param task Task description
     * @param start Start date of the event
     * @param end   End date of the event
     * @param isDone  True if the task is marked as done, false otherwise
     */
    public Event(String task, String start, String end, boolean isDone) {
        super(task, isDone);
        this.start = start;
        this.end = end;

        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return Formatted string representation of the event task
     */
    @Override
    public String printTask() {
        String startString;
        String endString;

        if (startDate != null) {
            startString = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            startString = this.start;
        }

        if (endDate != null) {
            endString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            endString = this.end;
        }
        return "[E]" + super.printTask() + " (from: " + startString + " to: " + endString + ")";
    }

    /**
     * Returns a string representation of the event task for storage purposes.
     * Format: "E | {marked} | {task} | {start} | {end}"
     *
     * @return String representation of the event task for storage
     */
    @Override
    public String toString() {
        int marked = (this.isDone) ? 1 : 0;
        return "E | " + marked + " | " + this.task + " | " + this.start + " | " + this.end;
    }

    public void setFromDate(String fromDate) {
        this.start = fromDate;

        try {
            startDate = LocalDate.parse(start);
        } catch (DateTimeParseException e) {
            startDate = null;
        }
    }

    public void setToDate(String toDate) {
        this.end = toDate;

        try {
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            endDate = null;
        }
    }
}
