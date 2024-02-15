package nicky.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import nicky.NickyException;

/**
 * Provides utility methods for parsing date-time strings in various formats.
 * It is used for handling date and time related functionality in the Nicky application.
 */
public class DateTimeUtil {
    private static final Logger LOGGER = Logger.getLogger(DateTimeUtil.class.getName());

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeString The date-time string to parse.
     * @return The LocalDateTime object representing the parsed date and time.
     * @throws NickyException If the date-time string is in an invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws NickyException {
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
            LOGGER.log(Level.FINE, "DateTime string does not match ISO_LOCAL_DATE_TIME format", e);
        }

        for (String format : dateTimeFormats) {
            try {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                LOGGER.log(Level.FINEST, "Trying different date-time format", e);
            }
        }

        throw new NickyException("Invalid date-time format. "
                + "Please use a valid format such as yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.");
    }
}
