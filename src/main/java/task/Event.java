package task;

import java.time.LocalDateTime;

import util.CsvUtil;
import util.DateTimeUtil;

/**
 * Represents an event task that has a start and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, from);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object with the specified marked status, description, start time, and end time.
     *
     * @param isMarked    the marked status of the event
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(boolean isMarked, String description, LocalDateTime from, LocalDateTime to) {
        super(isMarked, description, from);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats the event task into a CsvUtil object for exporting to CSV file.
     *
     * @return the CsvUtil object representing the formatted event task
     */
    @Override
    public CsvUtil format() {
        return new CsvUtil("E", String.valueOf(super.isMarked), super.description,
                DateTimeUtil.format(from),
                DateTimeUtil.format(to));
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateTimeUtil.format(from),
                DateTimeUtil.format(to));
    }

    /**
     * Checks if the event task is equal to another object.
     *
     * @param o the object to compare with
     * @return true if the event task is equal to the other object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event e = (Event) o;
        return isMarked == e.isMarked && description.equals(e.description)
                && e.from.equals(from) && e.to.equals(to);
    }
}
