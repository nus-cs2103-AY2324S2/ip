package datesandtimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.RyanGoslingDateTimeException;
import exceptions.RyanGoslingException;

/**
 * Parses and formats dates and times.
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
     * Validates the ordering of event dates and times.
     *
     * @param date1 The first date.
     * @param time1 The first time.
     * @param date2 The second date.
     * @param time2 The second time.
     * @throws RyanGoslingException If the event dates or times are not in the correct order.
     */
    public static void validateEventTimeAndDates(LocalDate date1, LocalTime time1,
                                                 LocalDate date2, LocalTime time2) throws RyanGoslingException {
        if (date1.isAfter(date2)) {
            throw new RyanGoslingDateTimeException("First date cannot be after second date!");
        }
        if (date1.isEqual(date2) && time1.isAfter(time2)) {
            throw new RyanGoslingDateTimeException("First time cannot be after second time on the same date!");
        }
    }



    /**
     * Tests the parseDate method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Example usage of parseDate
        System.out.println((LocalDate.parse("2023-02-23")));
        System.out.println(parseTimeAsLocalTime("09:00"));
    }
}
