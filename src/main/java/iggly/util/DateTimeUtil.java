package iggly.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link DateTimeUtil} class consists the method which formats the time.
 */
public class DateTimeUtil {
    /**
     * Format LocalDateTime using a specific DateTimeFormatter.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representation of the LocalDateTime.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        return dateTime.format(formatter);
    }

    /**
     * Format LocalDateTime using a specific DateTimeFormatter.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representation of the LocalDateTime.
     */
    public static String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        return dateTime.format(formatter);
    }

    /**
     * Format LocalDateTime using a specific DateTimeFormatter.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representation of the LocalDateTime.
     */
    public static String formatDate(LocalDate dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateTime.format(formatter) + ", " + dateTime.getDayOfWeek();
    }

}
