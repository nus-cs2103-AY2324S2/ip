package datesandtimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
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
    public static String parseTime(String timeString) {
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return time.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(parseDate(LocalDate.parse("2045-02-31")));
    }
}
