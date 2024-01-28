package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DateTimeUtil class provides utility methods for formatting and parsing date and time.
 */
public class DateTimeUtil {
    private static final DateTimeFormatter input = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Formats the given string representation of a date and time into a LocalDateTime object.
     *
     * @param s the string representation of a date and time
     * @return the LocalDateTime object representing the formatted date and time
     */
    public static LocalDateTime format(String s) {
        return LocalDateTime.parse(s, input);
    }

    /**
     * Formats the given LocalDateTime object into a string representation of a date and time.
     *
     * @param d the LocalDateTime object to be formatted
     * @return the string representation of the formatted date and time
     */
    public static String format(LocalDateTime d) {
        return d.format(input);
    }

    /**
     * Parses the given string representation of a date and time into a LocalDateTime object.
     *
     * @param s the string representation of a date and time
     * @return the LocalDateTime object representing the parsed date and time
     */
    public static LocalDateTime parse(String s) {
        return LocalDateTime.parse(s, input);
    }

    /**
     * Checks if the given string representation of a date is valid.
     *
     * @param dateStr the string representation of a date
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValid(String dateStr) {
        if (dateStr == null || dateStr.isEmpty() || dateStr.equals("null")) {
            return true;
        }
        try {
            LocalDate.parse(dateStr, input);
            return true;
        } catch (DateTimeParseException ignored) {
            return false;
        }
    }
}

