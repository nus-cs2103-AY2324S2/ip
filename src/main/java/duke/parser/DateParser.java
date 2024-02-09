package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a parser for date input.
 */
public class DateParser {
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
    );

    private static final List<DateTimeFormatter> DATE_ONLY_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.BASIC_ISO_DATE
    );

    /**
     * Parses string date input into appropriate LocalDateTime format
     *
     * @param input Date in String type.
     * @return Date in LocalDateTime type.
     * @throws IllegalArgumentException If unable to parse string date input.
     */
    public static LocalDateTime parseDateTime(String input) {
        // First, try parsing with date-only formatters
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            if (canParse(input.trim(), formatter)) {
                LocalDate date = LocalDate.parse(input.trim(), formatter);
                return date.atStartOfDay(); // Convert to LocalDateTime at start of the day
            }
        }

        // If date-only parsing fails, try date-time formatters
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            if (canParse(input.trim(), formatter)) {
                return LocalDateTime.parse(input.trim(), formatter);
            }
        }
        throw new IllegalArgumentException("Unable to parse date and time: " + input);
    }

    private static boolean canParse(String input, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
