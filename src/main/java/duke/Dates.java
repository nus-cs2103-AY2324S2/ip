package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Dates class handles validation and formatting of strings in input and database format
 */
public class Dates {
    // Looks for pattern "dd/MM/yyyy HHmm", used for input purposes
    static final Pattern DATE_INPUT_PATTERN = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/" +
            "(\\d{4}) (\\d{4}$)");

    // Looks for pattern "dd Month yyyy HH:mm", used for storing in db and output purposes
    static final Pattern DATE_DB_PATTERN = Pattern.compile("\\d{1,2} "
            + "(January|February|March|April|May|June|July|August|September|October|November|December)" +
            " \\d{4} \\d{2}:\\d{2}");

    // Used for parsing datetime string in input format, to a datetime object
    static final DateTimeFormatter DATE_INPUT_FORMATTER= DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    // Used for parsing a datetime string in db format, to a datetime object
    static final DateTimeFormatter DATE_DB_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    /**
     * Validates an input string and returns true if it matches
     *
     * @param date is the date in "dd/MM/yyyy HHmm" format
     * @return boolean if it matches
     */
    public static boolean isValidInputDate(String date) {
        Matcher matcher = Dates.DATE_INPUT_PATTERN.matcher(date);
        return matcher.matches();
    }

    /**
     * Checks that the db string date matches the db date pattern
     *
     * @param date is the date in "dd Month yyyy HH:mm" format
     * @return True if the date String matches the pattern
     */
    public static boolean isValidDbDate(String date) {
        Matcher matcher = DATE_DB_PATTERN.matcher(date);
        return matcher.matches();
    }

    /**
     * Creates a datetime object, using the input string formatter
     *
     * @param date which is a string that has a valid input pattern
     * @return LocalDateTime class based on the date String
     */
    public static LocalDateTime inputStr2DateTime(String date) {
        try {
            return LocalDateTime.parse(date, DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Could not parse date" + e.getMessage());
        }
        return null;
    }

    /**
     * Creates a datetime object, using the db string formatter
     *
     * @param date which is a string that has a valid db pattern
     * @return LocalDateTime class based on the date String
     */
    public static LocalDateTime dbStr2DateTime(String date) {
        try {
            return LocalDateTime.parse(date, DATE_DB_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Could not parse date" + e.getMessage());
        }
        return null;
    }

    /**
     * Converts a datetime object to its database string representation
     *
     * @param datetime object
     * @return String representation for database dates
     */
    public static String dateTime2DbStr(LocalDateTime datetime) {
        try {
            return datetime.format(Dates.DATE_DB_FORMATTER);
        } catch (DateTimeException e) {
            System.out.println("Failed to format datetime: " + e.getMessage());
        }
        return null;
    }
}