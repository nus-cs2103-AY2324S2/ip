package datesandtimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for parsing and formatting dates and times.
 */
public class DateTimeParser {

    /**
     * Parses a {@link LocalDate} into a formatted date string.
     *
     * @param date The LocalDate to be parsed.
     * @return A formatted date string in the "MMM d yyyy" format.
     */
    public static String parseDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Parses a time string into a formatted time string.
     *
     * @param timeString The time string to be parsed.
     * @return A formatted time string in the "h:mm a" format.
     * @throws DateTimeParseException If the time string cannot be parsed.
     */
    public static String parseTime(LocalTime timeString) {
        assert timeString != null : "LocalTime object should not be null!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return timeString.format(formatter);
    }

    /**
     * Parses a time string into a {@link LocalTime} object.
     *
     * @param timeString The time string to be parsed.
     * @return The parsed LocalTime object.
     * @throws DateTimeParseException If the time string cannot be parsed.
     */
    public static LocalTime parseTimeAsLocalTime(String timeString) {
        assert timeString != null : "String timeString should not be null!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, formatter);
    }

    /**
     * Main method for testing the parseDate method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Example usage of parseDate
        System.out.println(parseDate(LocalDate.parse("2045-02-28")));
    }
}
