package paimon.task;

import paimon.ChatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Provides functionality to parse date strings into {@link LocalDateTime} objects.
 * Supports multiple date and time formats for flexibility in user input.
 */
public class DateParser {
    private static final List<FormatterWithTime> formatters = Arrays.asList(new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"), true), new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy/MM/dd"), false), new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), true), new FormatterWithTime(DateTimeFormatter.ofPattern("yyyy-MM-dd"), false), new FormatterWithTime(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"), true), new FormatterWithTime(DateTimeFormatter.ofPattern("dd/MM/yyyy"), false));

    /**
     * Parses a string representing a date or datetime into a {@link LocalDateTime} object.
     * The method attempts to parse the input string using multiple predefined formats.
     * If the input does not match any of the supported formats, a {@link ChatException} is thrown.
     *
     * @param input The date string to be parsed.
     * @return A {@link LocalDateTime} object representing the specified date/time.
     * @throws ChatException If the input string cannot be parsed into a valid date/time.
     */
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

    /**
     * A helper class that pairs a {@link DateTimeFormatter} with a boolean indicating
     * whether the formatter includes a time component.
     */
    private static class FormatterWithTime {
        final DateTimeFormatter formatter;
        final boolean hasTime;

        /**
         * Constructs a FormatterWithTime.
         *
         * @param formatter The {@link DateTimeFormatter} used for parsing.
         * @param hasTime   Indicates whether the formatter includes a time component.
         */
        FormatterWithTime(DateTimeFormatter formatter, boolean hasTime) {
            this.formatter = formatter;
            this.hasTime = hasTime;
        }
    }
}