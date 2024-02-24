package james.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import james.exception.DukeException;

/**
 * Represents a deadline task.
 */
public class Event extends Task {
    private static final List<DateTimeFormatter> DFORMATTERS = Arrays.asList(
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("d-MM-yyyy"),
        DateTimeFormatter.ofPattern("dd-M-yyyy"),
        DateTimeFormatter.ofPattern("d-M-yyyy"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("d-MM-yyyy"),
        DateTimeFormatter.ofPattern("dd-M-yyyy"),
        DateTimeFormatter.ofPattern("d-M-yyyy"),

        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("d/MM/yyyy"),
        DateTimeFormatter.ofPattern("dd/M/yyyy"),
        DateTimeFormatter.ofPattern("d/M/yyyy"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("d/MM/yyyy"),
        DateTimeFormatter.ofPattern("dd/M/yyyy"),
        DateTimeFormatter.ofPattern("d/M/yyyy")
    );

    private static final List<DateTimeFormatter> DTFORMATTERS = Arrays.asList(
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
        DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"),
        DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy Hmm"),
        DateTimeFormatter.ofPattern("d-MM-yyyy Hmm"),
        DateTimeFormatter.ofPattern("dd-M-yyyy Hmm"),
        DateTimeFormatter.ofPattern("d-M-yyyy Hmm"),

        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
        DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"),
        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy Hmm"),
        DateTimeFormatter.ofPattern("d/MM/yyyy Hmm"),
        DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"),
        DateTimeFormatter.ofPattern("d/M/yyyy Hmm")
    );
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for an event task.
     *
     * @param description Description of the event.
     * @param from        Start date and time of the event.
     * @param to          End date and time of the event.
     * @throws DukeException If the date time format is invalid.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = parseDateTime(from);
        if (this.from == null) {
            throw new DukeException("Invalid date time format. Please use a recognized format.");
        }
        this.to = parseDateTime(to);
        if (this.to == null) {
            throw new DukeException("Invalid date time format. Please use a recognized format.");
        }
    }

    private LocalDateTime parseDateTime(String by) {
        for (DateTimeFormatter formatter : DTFORMATTERS) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException ignored) {
                //ignored because multiple formats are tried
            }
        }
        for (DateTimeFormatter formatter : DFORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(by, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ignored) {
                //ignored because multiple formats are tried
            }
        }
        try {
            LocalTime time = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
            return LocalDateTime.of(LocalDate.now(), time);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Converts a date time to a string.
     *
     * @param s Date time to be converted.
     * @return String representation of the date time.
     */
    public String datetoString(LocalDateTime s) {
        if (s == null) {
            return "Invalid date time format. Please use the format dd-MM-yyyy HHmm";
        }
        return s.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }

    /**
     * Converts a date time to a string for saving.
     *
     * @param s Date time to be converted.
     * @return String representation of the date time for saving.
     */
    public String datetoSaveString(LocalDateTime s) {
        if (s == null) {
            return "Invalid date time format. Please use the format dd-MM-yyyy HHmm";
        }
        return s.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return Start date and time of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end date and time of the event.
     *
     * @return End date and time of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Converts the event task to a string for saving.
     *
     * @return String representation of the event task for saving.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | from " + this.datetoSaveString(this.getFrom())
            + " to " + this.datetoSaveString(this.getTo());
    }

    /**
     * Gets the date time of the event.
     *
     * @return Date time of the event.
     */
    @Override
    public LocalDateTime getDateTime() {
        return this.getFrom();
    }

    /**
     * Converts the event task to a string.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.datetoString(this.getFrom())
            + " to: " + this.datetoString(this.getTo()) + ")";
    }
}
