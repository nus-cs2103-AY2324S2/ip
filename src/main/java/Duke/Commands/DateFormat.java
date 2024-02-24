package Duke.Commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for formatting and comparing LocalDate objects.
 */
public class DateFormat {

    /**
     * Formats a date string into a LocalDate object using the pattern "yyyy-MM-dd".
     *
     * @param date The date string to be formatted.
     * @return The LocalDate object representing the formatted date.
     */
    public static LocalDate format(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Compares two LocalDate objects to check if the second date is after the first date.
     *
     * @param d1 The first LocalDate object.
     * @param d2 The second LocalDate object.
     * @return True if the second date is after the first date, false otherwise.
     */
    public static boolean compareDate(LocalDate d1, LocalDate d2) {
        return d2.isAfter(d1);
    }

    /**
     * Reformats a LocalDate object into a string using the pattern "MMM dd yyyy".
     *
     * @param date The LocalDate object to be reformatted.
     * @return The string representation of the reformatted date.
     */
    public static String reformatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }
}
