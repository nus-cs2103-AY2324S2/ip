package floofy.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
/**
 * Represents an event task.
 */
public class Event extends Task {
    /** The start date of the event */
    protected LocalDate from;
    /** The end date of the event */
    protected LocalDate to;

    /**
     * Constructs a new object of the Event class.
     *
     * @param description The description of the event.
     * @param eventFromDate The start date of the event.
     * @param eventToDate The end date of the event.
     */
    public Event(String description, LocalDate eventFromDate, LocalDate eventToDate) {
        super(description);
        this.from = eventFromDate;
        this.to = eventToDate;
    }

    /**
     * Converts the date from LocalDate to String format.
     *
     * @param date The date to be converted into a string.
     * @return The date in a string format.
     */
    //NOTE TO SELF: OOP TO TASK!!
    public String getDateString(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    /**
     * Returns the String form of the task type (Event) - "E".
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the String of an Event task in its file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + String.format(" | %s - %s", getDateString(this.from), getDateString(this.to));
    }

    /**
     * Returns a string form of an Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
