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
public class Deadline extends Task {
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
    private LocalDateTime by;

    /**
     * Constructor for a deadline task.
     *
     * @param description Description of the deadline task.
     * @param by          Date and time of the deadline.
     * @throws DukeException If the date and time format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = parseDateTime(by);
        if (this.by == null) {
            throw new DukeException("Invalid date time format. Please use a recognized format.");
        }
    }

    private LocalDateTime parseDateTime(String by) {
        for (DateTimeFormatter formatter : DTFORMATTERS) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException ignored) {
                // Ignored
            }
        }
        for (DateTimeFormatter formatter : DFORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(by, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ignored) {
                // Ignored
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
     * Converts the date and time to a string.
     *
     * @param s Date and time to be converted.
     * @return Date and time in string format.
     */
    public String datetoString(LocalDateTime s) {
        return s.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }

    /**
     * Converts the date and time to a string for saving.
     *
     * @param s Date and time to be converted.
     * @return Date and time in string format for saving.
     */
    public String datetoSaveString(LocalDateTime s) {
        if (s == null) {
            return "Invalid date time format. Please use the format dd-MM-yyyy HHmm";
        }
        return s.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Gets the date and time of the deadline.
     *
     * @return Date and time of the deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Converts the deadline task to a string for saving.
     *
     * @return Deadline task in string format for saving.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + this.datetoSaveString(this.getBy());
    }

    /**
     * Gets the date and time of the deadline.
     *
     * @return Date and time of the deadline.
     */
    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Converts the deadline task to a string.
     *
     * @return Deadline task in string format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetoString(this.getBy()) + ")";
    }
}
