package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an "Event" task in the Duke application, which includes start and
 * end dates.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    private String startToEnd;

    /**
     * Constructs a new Event task with the specified description, start and end
     * dates.
     *
     * @param description The task's description.
     * @param startToEnd  A string representing the range from start to end date.
     * @param input       The original input string that created the task.
     * @param start       The start date of the event.
     * @param end         The end date of the event.
     */
    Event(String description, String startToEnd, String input, LocalDate start, LocalDate end) {
        super(description, input);
        this.start = start;
        this.end = end;
        this.startToEnd = formatStartToEnd();
    }

    /**
     * Formats the start and end dates of the Event task.
     *
     * @return A formatted string representing the range from start to end date.
     */
    private String formatStartToEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.start.format(formatter) + " to: " + this.end.format(formatter);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string that includes the task's description and date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startToEnd + ")";
    }

    /**
     * Marks the Event task as done and prints a confirmation message.
     */
    @Override
    public String markComplete() {
        super.setComplete();
        return "\tNice! I've marked this task as done:\n\t" + this.toString();
    }

    /**
     * Marks the Event task as not done and prints a confirmation message.
     */
    @Override
    public String unmarkComplete() {
        super.setIncomplete();
        return "\tOK, I've marked this task as not done yet:\n\t" + this.toString();
    }

    /**
     * Returns a string format of the Event task for file storage.
     *
     * @return A string representation of the Event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("Event | %s | %s", super.statusNumber, super.input);
    }
}
