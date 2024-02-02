package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that provides methods to parse and format date strings.
 */
public class DateChecker {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses the given date string into a LocalDate object.
     *
     * @param dateString The date string to be parsed.
     * @return The LocalDate object represented by the date string.
     * @throws DukeException If the date string is not in the expected format.
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Formats a LocalDate object into a string.
     *
     * @param date The LocalDate object to be formatted.
     * @return The formatted date string (MMM dd yyyy).
     */
    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_FORMAT);
    }
}
