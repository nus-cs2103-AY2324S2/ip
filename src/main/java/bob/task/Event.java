package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import bob.Storage;
import bob.Ui;

/**
 * Represents an event with a specified description, start time and end time.
 * An <code>Event</code> object corresponds to a task with a description, a start time and an end time.
 */
public class Event extends Task {
    public static final String STORAGE_INDICATOR = "E";

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Returns an event with a specified description, start time and end time.
     *
     * @param description The description for the event.
     * @param from The start time for the event.
     * @param to The end time for the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns storage format of the event.
     *
     * @return The string representation of the event in storage.
     */
    @Override
    public String getStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.getStorageFormat() + " | "
                + Storage.formatDateTime(from) + " | " + Storage.formatDateTime(to);
    }

    /**
     * Returns whether the event is occurring on a specified day.
     *
     * @param date The date on which the event is said to occur on.
     * @return Whether the event is occurring on the specified date.
     */
    @Override
    public boolean isOccurringOn(LocalDate date) {
        return !(date.isBefore(from.toLocalDate()) || date.isAfter(to.toLocalDate()));
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Ui.formatDateTime(from)
                + " to: " + Ui.formatDateTime(to) + ')';
    }
}
