package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start date and an end date.
 */
public class Event extends Task {

    protected LocalDate start; // Start date of the event
    protected LocalDate end; // End date of the event

    /**
     * Constructs an Event object with the specified description, start date, and end date.
     *
     * @param description Description of the event.
     * @param start       Start date of the event.
     * @param end         End date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type ID of the task, which is "E" for Event.
     *
     * @return The type ID of the event task.
     */
    @Override
    public String typeid() {
        return "E";
    }

    /**
     * Returns a string representation of the start and end dates of the event in the format "~start~end".
     *
     * @return A string containing the start and end dates of the event.
     */
    public String timeprint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startstring = formatter.format(start);
        String endstring = formatter.format(end);
        return ("~" + startstring + "~" + endstring);
    }

    /**
     * Returns a string representation of the event task including its description and dates.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
