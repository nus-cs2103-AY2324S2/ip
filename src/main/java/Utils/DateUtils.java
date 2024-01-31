package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    // 03/21/1999 1600
    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy, ha");
    public static LocalDateTime parseDate(String dateString) {
        try {
            return LocalDateTime.parse(dateString, INPUT_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date string given. Expected format is d/M/yyyy HHmm (e.g. 21/10/1999 1600)");
        }
    }

    public static String formatDate(LocalDateTime dateTime) {
        try {
            return dateTime.format(OUTPUT_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date string given. Expected format is d/M/yyyy HHmm (e.g. 21/10/1999 1600)");
        }
    }
}
