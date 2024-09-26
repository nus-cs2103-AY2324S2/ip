package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles validation and formatting of date strings for input and database.
 */

public class DateHandler {

    // Pattern for input dates to ensure format of "dd/MM/yyyy HHmm" is maintained
    // Use of input date patterns inspired by team member code
    static final Pattern INPUT_DATE_PATTERN = Pattern.compile(
            "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/(\\d{4}) (\\d{4}$)");

    // Formatter for parsing datetime strings in input format to datetime objects
    static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    // Formatter for parsing datetime strings in stored format to datetime objects
    static final DateTimeFormatter OBJ_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    /**
     * Converts a date string in input format to a LocalDateTime object.
     *
     * @param dateinput the date string in "dd/MM/yyyy HHmm" format.
     * @return a LocalDateTime object representing the parsed date.
     */

    public static LocalDateTime inputStringDateTime(String dateinput) {
        try {
            return LocalDateTime.parse(dateinput, DateHandler.INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Unable to read the date" + e.getMessage());
        }
        return null;
    }

    /**
     * Converts a LocalDateTime object to its database string representation.
     *
     * @param datetime the LocalDateTime object to convert.
     * @return a string representation of the date in database format.
     */
    public static String objDateTime(LocalDateTime datetime) {
        try {
            return datetime.format(DateHandler.OBJ_DATE_FORMATTER);
        } catch (DateTimeException e) {
            System.out.println("Error: Failed to format date" + e.getMessage());
        }
        return null;
    }

    /**
     * Checks an input string representing a date in "dd/MM/yyyy HHmm" format.
     *
     * @param dateinput the date string to validate.
     * @return true if the date string matches the expected format.
     */
    public static boolean isValidInputDate(String dateinput) {
        Matcher datematcher = DateHandler.INPUT_DATE_PATTERN.matcher(dateinput);
        return datematcher.matches();
    }
}
