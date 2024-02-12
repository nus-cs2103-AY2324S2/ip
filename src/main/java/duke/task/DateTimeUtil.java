/*
 * DateTimeUtil.java
 * This class provides utility methods for parsing date-time strings in various formats.
 * It is used for handling date and time related functionality in the Duke application.
 */

package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Provides utility methods for parsing date-time strings in various formats.
 */
public class DateTimeUtil {
    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeString The date-time string to parse.
     * @return The LocalDateTime object representing the parsed date and time.
     * @throws DukeException If the date-time string is in an invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String[] dateTimeFormats = {
            "yyyy-MM-dd HH:mm", "dd/MM/yyyy HH:mm", "MM/dd/yyyy HH:mm",
            "yyyy-MM-dd HHmm", "dd/MM/yyyy HHmm", "MM/dd/yyyy HHmm",
            "dd-MM-yyyy HH:mm", "dd-MM-yyyy HHmm", "d-MM-yyyy HH:mm", "d-MM-yyyy HHmm",
            "dd-M-yyyy HH:mm", "dd-M-yyyy HHmm", "d-M-yyyy HH:mm", "d-M-yyyy HHmm",
            "d/M/yyyy HH:mm", "d/M/yyyy HHmm", "dd/M/yyyy HH:mm", "dd/M/yyyy HHmm",
            "m/d/yyyy HH:mm", "m/d/yyyy HHmm", "mm/d/yyyy HH:mm", "mm/d/yyyy HHmm"
        };

        try {
            return LocalDateTime.parse(dateTimeString, isoFormatter);
        } catch (DateTimeParseException e) {
            // Ignore and try other formats
        }

        for (String format : dateTimeFormats) {
            try {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        throw new DukeException("Invalid date-time format. "
                + "Please use a valid format such as yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.");
    }
}
