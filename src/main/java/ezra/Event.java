package ezra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific start and end time.
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected String startString;
    protected String endString;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param start       The start time of the event in the format "dd/MM/yyyy HHmm".
     * @param end         The end time of the event in the format "dd/MM/yyyy HHmm".
     * @throws DateTimeParseException If the start or end time format is invalid.
     */
    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        this.startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.startString = start;
        this.endString = end;
    }

    /**
     * Returns a formatted string representation of the Event object to display to the user.
     *
     * @return A formatted string including task type, status, description, start time, and end time.
     */
    @Override
    public String toString() {
        String startString = this.startDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        String endString = this.endDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                this.description,
                startString,
                endString);
    }

    /**
     * Returns a formatted string representation of the Event object for storage purposes.
     *
     * @return A formatted string suitable for storage, including task type, status, description, start and end time.
     */
    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                this.startString,
                this.endString);
    }

    /**
     * Checks if this Event object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event e = (Event) o;
        return this.description.equals(e.description)
                && this.startString.equals(e.startString)
                && this.endString.equals(e.endString);
    }

    @Override
    public Event copy() {
        Event copy = new Event(this.description, this.startString, this.endString);
        copy.isDone = this.isDone;
        return copy;
    }
}
