package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    // Method to parse LocalDateTime based on input length
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

    // Method to format LocalDateTime as "dd-MM-yyyy HHmm"
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
