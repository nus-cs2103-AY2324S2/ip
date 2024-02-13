package paimon;

import paimon.ChatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class DateParser {
    private static final List<FormatterWithTime> formatters = Arrays.asList(
            new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"), true),
            new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy/MM/dd"), false),
            new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), true),
            new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy-MM-dd"), false),
            new FormatterWithTime(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"), true),
            new FormatterWithTime(DateTimeFormatter.ofPattern("dd/MM/yyyy"), false)
    );
    public static LocalDateTime parseDate(String input) throws ChatException {
        LocalDateTime dateTime = null;
        for (FormatterWithTime formatterWithTime : formatters) {
            try {
                // Attempt to parse as LocalDateTime
                if (formatterWithTime.hasTime) {
                    // Check if the formatter expects time component
                    return LocalDateTime.parse(input, formatterWithTime.formatter);
                } else {
                    // Formatter is for LocalDate, need to convert to LocalDateTime
                    LocalDate date = LocalDate.parse(input, formatterWithTime.formatter);
                    return date.atStartOfDay(); // Converts LocalDate to LocalDateTime at start of the day
                }
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new ChatException("The date should be in the format: yyyy/MM/dd, yyyy/MM/dd HH:mm, yyyy-MM-DD, dd/MM/yyyy HH:mm");
    }

    private static class FormatterWithTime {
        final DateTimeFormatter formatter;
        final boolean hasTime;

        FormatterWithTime(DateTimeFormatter formatter, boolean hasTime) {
            this.formatter = formatter;
            this.hasTime = hasTime;
        }
    }
}