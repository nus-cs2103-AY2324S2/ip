import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateTimeParser class
 */
public class DateTimeParser {

    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            // Handle parsing exception or return null based on your requirements
            // e.printStackTrace();
            return null;
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
        } catch (DateTimeParseException e) {
            // Handle parsing exception or return null based on your requirements
            // e.printStackTrace();
            return null;
        }
    }

    public static LocalTime parseTime(String timeString) {
        try {
            return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeParseException e) {
            // Handle parsing exception or return null based on your requirements
            // e.printStackTrace();
            return null;
        }
    }

    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}