package yoda.datetimeutil;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

public class DateTimeUtil {
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd MMM"), // e.g., 30 Jan
            DateTimeFormatter.ofPattern("MMM dd"), // e.g., Jan 30
            DateTimeFormatter.ofPattern("MMM dd yyyy"), // e.g., Jan 30 2024
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm"), // e.g., Jan 30 2024 1306
            DateTimeFormatter.ofPattern("yyyy-MM-dd "), // e.g., 2024-01-30
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // e.g., 2024-01-30 1306
            DateTimeFormatter.ofPattern("d MMM yyyy"), // e.g., 30 Jan 2024
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"), // e.g., 30 Jan 2024 1306
            DateTimeFormatter.ofPattern("d MMMM yyyy"), // e.g., 30 January 2024
            DateTimeFormatter.ofPattern("d MMMM yyyy HHmm"), // e.g., 30 January 2024 1306
            DateTimeFormatter.ofPattern("dd/MM/yyyy"), // e.g., 30/01/2024
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"), // e.g., 30/01/2024 1306
            DateTimeFormatter.ofPattern("dd-MM-yyyy"), // e.g., 30-01-2024
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"), // e.g., 30-01-2024 1306
            DateTimeFormatter.ofPattern("yyyy/MM/dd"), // e.g., 2024/01/30
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"), // e.g., 2024/01/30 1306
            DateTimeFormatter.ofPattern("yyyyMMdd"),   // e.g., 20240130
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm")   // e.g., 20240130 1306
    );

    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
        throw new DateTimeParseException("Failed to parse date-time: " + dateTimeStr, dateTimeStr, 0);
    }

}
