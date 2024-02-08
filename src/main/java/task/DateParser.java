package main.java.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class DateParser {
    private static final List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    );
    public static LocalDateTime parseDate(String input) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                // Attempt to parse as LocalDateTime
                if (formatter.toString().contains("HH")) { // Check if the formatter expects time component
                    return LocalDateTime.parse(input, formatter);
                } else { // Formatter is for LocalDate, need to convert to LocalDateTime
                    LocalDate date = LocalDate.parse(input, formatter);
                    return date.atStartOfDay(); // Converts LocalDate to LocalDateTime at start of the day
                }
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }

        if (dateTime == null) {
            throw new DateTimeParseException("Not able to parse", input, 0);
        } else {
            return dateTime;
        }
    }
}