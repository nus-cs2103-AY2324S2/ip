package bytebuddy.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

/**
 * The Event class represents a task that occurs within a specified time frame.
 * It extends the Task class and includes additional properties for start and end times.
 */
public class Event extends Task {

    protected String from;
    protected String to;
    protected Optional<LocalDate> fromDate;
    protected Optional<LocalDateTime> fromDateTime;
    protected Optional<LocalDate> toDate;
    protected Optional<LocalDateTime> toDateTime;

    /**
     * Constructs a new Event task with the given description, start, and end times.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);

        this.fromDate = parseDate(from);
        this.fromDateTime = parseDateTime(from);
        // Initialise 'by' string depending on type, else use given 'by' string as is
        if (fromDate.isPresent() || fromDateTime.isPresent()) {
            this.from = formatFromString(from);
        } else {
            this.from = from;
        }

        this.toDate = parseDate(to);
        this.toDateTime = parseDateTime(to);
        // Initialise 'by' string depending on type, else use given 'by' string as is
        if (toDate.isPresent() || toDateTime.isPresent()) {
            this.to = formatToString(from);
        } else {
            this.to = to;
        }
    }

    /**
     * Constructs a new Event task with completion status, description, start, and end times.
     *
     * @param completed   The completion status of the event (1 for done, 0 for not done).
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String completed, String description, String from, String to) {
        super(description, completed.equals("1"));
        this.from = from;
        this.to = to;
    }

    /**
     * Parses the date and time from the input string.
     *
     * @param s The input string containing date and time information.
     * @return An Optional containing LocalDateTime if parsing is successful, empty otherwise.
     */
    private Optional<LocalDateTime> parseDateTime(String s) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return Optional.of(LocalDateTime.parse(s, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Parses the date from the input string.
     *
     * @param s The input string containing date information.
     * @return An Optional containing LocalDate if parsing is successful, empty otherwise.
     */
    private Optional<LocalDate> parseDate(String s) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Optional.of(LocalDate.parse(s, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Formats the 'from' string based on the type of date or date-time.
     *
     * @param standard The default string representation if no special formatting is required.
     * @return The formatted 'from' string.
     */
    private String formatFromString(String standard) {
        if (fromDateTime.isPresent()) {
            return fromDateTime.get().format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha"));
        } else if (fromDate.isPresent()) {
            return fromDate.get().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return standard;

    }

    /**
     * Formats the 'to' string based on the type of date or date-time.
     *
     * @param standard The default string representation if no special formatting is required.
     * @return The formatted 'to' string.
     */
    private String formatToString(String standard) {
        if (toDateTime.isPresent()) {
            return toDateTime.get().format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha"));
        } else if (toDate.isPresent()) {
            return toDate.get().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return standard;
    }

    /**
     * Returns a formatted string representation of the Event task for writing into output file.
     *
     * @return The formatted output string.
     */
    @Override
    public String getTextFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("E | %d | %s | %s | %s", intIsDone, description, from, to);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method considers two Event objects equal if they have the same description, completion status,
     * start time, and end time.
     *
     * @param obj the reference object with which to compare.
     * @return true if this Event is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        return isDone == event.isDone
                && Objects.equals(description, event.description)
                && Objects.equals(from, event.from)
                && Objects.equals(to, event.to);
    }

    /**
     * Returns a hash code value for the Event object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, from, to);
    }


    /**
     * Returns a string representation of the Event task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
