package bytebuddy.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        // init from string depending on type, else use given by string
        if (fromDate.isPresent() || fromDateTime.isPresent()) {
            this.from = formatFromString(from);
        } else {
            this.from = from;
        }

        this.toDate = parseDate(to);
        this.toDateTime = parseDateTime(to);
        // init from string depending on type, else use given by string
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
     * Returns a string representation of the Event task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
