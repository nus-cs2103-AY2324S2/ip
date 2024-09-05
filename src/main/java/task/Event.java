package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The Event class represents a task with a start date and an end date.
 * It extends the Task class and includes methods to query by date and provide a formatted string representation.
 */
public class Event extends Task {

    /** The start date of the event. */
    private LocalDate startDate;

    /** The end date of the event. */
    private LocalDate endDate;

    /**
     * Constructs an Event task with the specified name, start date, and end date.
     * @param name The name of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        assert startDate.isBefore(endDate) || startDate.isEqual(endDate) : "Start date must be before end date";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Queries whether the event occurs on the specified date.
     * @param date The date to be queried.
     * @return true if the event occurs on the specified date, false otherwise.
     */
    public boolean queryByDate(LocalDate date) {
        return (date.isAfter(this.startDate) && date.isBefore(this.endDate))
                || date.isEqual(this.startDate) || date.isEqual(this.endDate);
    }

    /**
     * Provides a formatted string representation of the Event task.
     * @return A string representing the Event task in a readable format.
     */
    @Override
    public String toString() {
        String from = this.startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String to = this.endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return String.format("[E]%s %s (from: %s to: %s)", (super.isMarked ? "[X]" : "[ ]"), super.name, from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.name.equals(event.name) && this.startDate.equals(event.endDate)
                    && this.endDate.equals(event.endDate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        String hashString = String.format("event,%s,%s,%s", name, startDate.toString(), endDate.toString());
        return hashString.hashCode();
    }
}
