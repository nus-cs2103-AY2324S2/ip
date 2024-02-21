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

    // Pattern for matching input dates in the format of "dd/MM/yyyy HHmm"
    static final Pattern INPUT_DATE_PATTERN = Pattern.compile(
            "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/(\\d{4}) (\\d{4}$)");

    // Formatter for parsing datetime strings in input format to datetime objects
    static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    // Pattern for matching database dates in the format of "dd Month yyyy HH:mm"
    static final Pattern DATABASE_DATE_PATTERN = Pattern.compile(
            "\\d{1,2} (January|February|March|April|May|June|July|August|September|October|November|December) "
                    + "\\d{4} \\d{2}:\\d{2}");

    // Formatter for parsing datetime strings in database format to datetime objects
    static final DateTimeFormatter DATABASE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

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

    /**
     * Checks if a date string matches the database date pattern "dd Month yyyy HH:mm".
     *
     * @param dateinput the date string to check.
     * @return true if the date string matches the expected format.
     */

    public static boolean isValidDbInputDate(String dateinput) {
        Matcher datematcher = DateHandler.DATABASE_DATE_PATTERN.matcher(dateinput);
        return datematcher.matches();
    }

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
     * Converts a date string in database format to a LocalDateTime object.
     *
     * @param dateinput the date string in "dd Month yyyy HH:mm" format.
     * @return a LocalDateTime object representing the parsed date.
     */

    public static LocalDateTime databaseStringDateTime(String dateinput) {
        try {
            return LocalDateTime.parse(dateinput, DateHandler.DATABASE_DATE_FORMATTER);
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
    public static String dateTimeToDatabaseString(LocalDateTime datetime) {
        try {
            return datetime.format(DateHandler.DATABASE_DATE_FORMATTER);
        } catch (DateTimeException e) {
            System.out.println("Error: Failed to format date" + e.getMessage());
        }
        return null;
    }
}
