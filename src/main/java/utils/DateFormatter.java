package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DateFormatter class provides utility methods to format dates.
 */
public class DateFormatter {
    /**
     * Formats the given unformatted date string.
     *
     * @param unformattedDate The unformatted date string.
     * @return The formatted date string.
     */
    public static String formatDate(String unformattedDate) {
        String[] dateTime = unformattedDate.split(" ");
        try {
            LocalDate date = LocalDate.parse(dateTime[0]);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + dateTime[1];
        } catch (DateTimeParseException e) {
            // If parsing fails, return the original unformatted date string
            return unformattedDate;
        }
    }
}
