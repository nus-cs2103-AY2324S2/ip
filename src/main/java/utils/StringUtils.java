package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains utility methods for parsing and formatting date and time strings.
 */
public class StringUtils {

    /**
     * This method parses a string into a LocalDateTime object.
     * @param dateString String to be parsed.
     * @return LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter formatter;
        if (dateString.length() > 10) {
            formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            return LocalDateTime.parse(dateString, formatter);
        } else {
            formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            return LocalDate.parse(dateString, formatter).atStartOfDay();
        }
    }
/**
     * This method formats a LocalDateTime object into a string.
     * @param dateTime LocalDateTime object to be formatted.
     * @return Formatted string.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
