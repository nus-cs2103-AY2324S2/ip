package duke.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Utils class provides various utility methods for working with date, time, and integer values.
 */
public class Utils {
    private static final String FORMAT = "d/M/yyyy HHmm";

    /**
     * Checks if a given string represents a valid date and time in a specific format.
     * 
     * @param dateTime The dateTime parameter is a string representing a date and time in a specific
     *                  format.
     * @return The method is returning a boolean value. It returns true if the given dateTime string is
     *         a valid date and time according to the specified format, and false otherwise.
     */
    public static boolean isValidDateTimeFormat(String dateTime) {
        dateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        try {
            LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * The function checks if two date-time strings are valid and if the "from" date-time is before or
     * equal to the "to" date-time.
     * 
     * @param fromDateTime The starting date and time.
     * @param toDateTime The ending date and time.
     * @return The method is returning a boolean value. It returns true if the fromDateTime is before or
     *         equal to the toDateTime, and false otherwise. If either of the date strings cannot be
     *         parsed into LocalDateTime objects, it returns false.
     */
    public static boolean isValidDateTimeFormat(String fromDateTime, String toDateTime) {
        fromDateTime = fromDateTime.trim();
        toDateTime = fromDateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        try {
            LocalDateTime from = LocalDateTime.parse(fromDateTime, formatter);
            LocalDateTime to = LocalDateTime.parse(toDateTime, formatter);
            return (from.isBefore(to) || from.isEqual(to)) ? true : false;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns a formatted string representation of the date in the format "dd MMMM yyyy".
     * 
     * @param dateTime The `dateTime` parameter is a `LocalDateTime` object representing a specific date
     *        and time.
     * @return The method is returning a formatted string representation of the given LocalDateTime
     *         object. The format of the string is "dd MMMM yyyy", which represents the day of the
     *         month, the full month name, and the year.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

    /**
     * Returns a formatted string representation of the date and time.
     * 
     * @param dateTime A LocalDateTime object representing a date and time.
     * @return The method is returning a formatted string representation of the given LocalDateTime
     *         object.
     */
    public static String formatInput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT));
    }

    /**
     * Takes a string representation of a date and time and returns a `LocalDateTime` object.
     * 
     * @param dateTime A string representation of a date and time in a specific format.
     * @return The method is returning a LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Checks if a given string can be parsed into an integer.
     * 
     * @param input The input parameter is a string that represents a number.
     * @return The method is returning a boolean value. It returns true if the input string can be
     *         parsed into an integer, and false if it cannot.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
