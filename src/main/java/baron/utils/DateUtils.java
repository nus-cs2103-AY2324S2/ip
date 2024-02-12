package baron.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Manages parsing and formatting of dates.
 */
public class DateUtils {

    // 03/21/1999 1600
    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy, ha");

    /**
     * Parses String and outputs a local datetime.
     *
     * @param dateString Takes in a datetime string with a format of d/M/yyy HHmm, e.g. 6/10/1999 2100
     * @return the parsed local datetime
     * @throws Exception when an invalid date string was given
     */
    public static LocalDateTime parseDate(String dateString) {
        try {
            return LocalDateTime.parse(dateString, INPUT_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date string given. Expected format is "
                                                + "d/M/yyyy HHmm (e.g. 21/10/1999 1600)");
        }
    }

    /**
     * Formats date to specified format of 9/12/1999, 4PM. Used for human friendly date strings mainly.
     * Dates are stored in a different format.
     *
     * @param dateTime date time to format to string
     * @return returns in format of d/MM/yyyy, ha (e.g. /12/1999, 4PM)
     */
    public static String formatDate(LocalDateTime dateTime) {
        try {
            return dateTime.format(OUTPUT_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date string given. Expected format is "
                                               + "d/M/yyyy HHmm (e.g. 21/10/1999 1600)");
        }
    }
}
