package cvb.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cvb.exceptions.ConvoBotException;

/**
 * Provides utility methods for handling date and time operations.
 * Includes methods for converting between {@code LocalDate} objects and formatted strings,
 * as well as handling exceptions related to date parsing.
 */
public class DateTime {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Converts a {@code LocalDate} object to a formatted string suitable for display.
     *
     * @param d The {@code LocalDate} object to be converted.
     * @return A formatted string representing the date for display.
     */
    public static String dateToString(LocalDate d) {
        return d.format(DISPLAY_FORMATTER);
    }

    /**
     * Converts a {@code LocalDate} object to a formatted string suitable for file storage.
     *
     * @param d The {@code LocalDate} object to be converted.
     * @return A formatted string representing the date for file storage.
     */
    public static String dateToFile(LocalDate d) {
        return d.format(INPUT_FORMATTER);
    }

    /**
     * Converts a formatted string to a {@code LocalDate} object.
     *
     * @param s The formatted string representing the date.
     * @return A {@code LocalDate} object parsed from the input string.
     * @throws ConvoBotException If the input string is in an invalid date format.
     */
    public static LocalDate stringToDate(String s) throws ConvoBotException {
        LocalDate d;
        try {
            d = LocalDate.parse(s, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ConvoBotException("Invalid input. Wrong date format.");
        }
        return d;
    }
}
