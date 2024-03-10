package micromanager.dataprocessing;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import micromanager.exceptions.DukeException;

/**
 * DateTimeParser class provides methods to parse date strings into LocalDate objects.
 */
public class DateTimeParser {
    /**
     * Parses the specified date string into a LocalDate object.
     *
     * @param date The date string to parse.
     * @return The parsed LocalDate object.
     * @throws DukeException If an error occurs during parsing.
     */
    public static LocalDate parse(String date) throws DukeException {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            if (parsedDate.isBefore(LocalDate.now())) {
                throw new DukeException("OOPS!!! The date you provided is already passed.");
            }
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! deadline should be in the format: yyyy-mm-dd.\n"
                    + "Please check if your date is valid");
        }
    }
}
