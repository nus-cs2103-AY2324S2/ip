package alpa.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Utility class for parsing and formatting date and time values.
 */
public class DateTimeUtils {

    /**
     * Array of date patterns used for parsing and formatting dates.
     * The patterns include variations with and without the year component, as well as patterns with month names.
     */
    private static final String[] DATE_PATTERNS = {
        "d/M/yyyy",
        "d-M-yyyy",
        "d/M/yy",
        "d-M-yy",
        "dMMyyyy",
        "dMMyy",
        "dd/MM/yyyy",
        "dd/MM/yy",
        "dd-MM-yyyy",
        "dd-MM-yy",
        "ddMMyyyy",
        "ddMMyy",
        "d/M",
        "d/MMM/yyyy",
        "d/MMM/yy",
        "d/MMM",
        "MMM/d",
        "d MMM yyyy",
        "MMM d yyyy",
        "d MMM",
        "MMM d",
    };

    /**
     * Array of date patterns without the year component.
     * Used by the parseDeadlineDateTime method to parse date-only strings
     * without a year component when time is not specified.
     */
    private static final String[] DATE_WITHOUT_YEAR_PATTERNS = {
        "d/M",
        "d/MMM",
        "MMM/d",
        "d MMM",
        "MMM d",
    };

    /**
     * An array of time patterns used for parsing and formatting time values.
     * The patterns include 12-hour and 24-hour clock format and variations with and without minutes,
     * as well as patterns with AM/PM indicators.
     */
    private static final String[] TIME_PATTERNS = {
        "HHmm",
        "HH:mm",
        "h:mm a",
        "h:mma",
        "h a",
        "ha",
    };

    /**
     * A list of DateTimeFormatters used for parsing and formatting date and time values.
     */
    private static final List<DateTimeFormatter> FORMATTERS = new ArrayList<>();

    static {
        for (String datePattern : DATE_PATTERNS) {
            for (String timePattern : TIME_PATTERNS) {
                DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                            .appendPattern(datePattern + " " + timePattern)
                            .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
                            .parseCaseInsensitive();

                // Check if the time pattern involves AM/PM notation
                if (timePattern.contains("a")) {
                    // For AM/PM time patterns, default CLOCK_HOUR_OF_AMPM and AMPM_OF_DAY if necessary
                    builder.parseDefaulting(ChronoField.CLOCK_HOUR_OF_AMPM, 11)
                        .parseDefaulting(ChronoField.AMPM_OF_DAY, 1); // Default to PM if not specified
                } else {
                    // For 24-hour time patterns or date-only, default to the end of the day
                    builder.parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59);
                }
                DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);
                FORMATTERS.add(formatter);
            }

            // Add a formatter for date only, defaulting to end of the day
            DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                        .appendPattern(datePattern)
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                        .parseCaseInsensitive()
                        .toFormatter(Locale.ENGLISH);
            FORMATTERS.add(dateFormatter);
        }
    }

    /**
     * Parses a string representation of date and time into a {@link LocalDateTime} object.
     *
     * @param dateTimeStr the string representation of date and time
     * @return the parsed {@link LocalDateTime} object
     * @throws DateTimeParseException if the input string is not in a valid date and time format
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        // Preprocess the input string to normalize formatting
        String normalizedDateTimeStr = normalizeDateTimeString(dateTimeStr);

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(normalizedDateTimeStr, formatter);
            } catch (DateTimeParseException ignored) {
                // Continue trying with the next formatter
            }
        }
        throw new DateTimeParseException("Invalid date or time format", dateTimeStr, 0);
    }

    /**
     * Normalizes a given date-time string by removing leading and trailing whitespace,
     * normalizing space around slashes (/) and dashes (-) for dates,
     * normalizing space before AM/PM markers, and ensuring AM/PM markers are uppercase.
     *
     * @param dateTimeStr the date-time string to be normalized
     * @return the normalized date-time string
     */
    private static String normalizeDateTimeString(String dateTimeStr) {
        // Trim leading and trailing whitespace
        String normalized = dateTimeStr.trim();

        // Normalize space around slashes (/) and dashes (-) for dates
        normalized = normalized.replaceAll("\\s*/\\s*", "/");
        normalized = normalized.replaceAll("\\s*-\\s*", "-");

        // Normalize space before AM/PM markers to ensure consistent formatting
        normalized = normalized.replaceAll("\\s+(AM|am|PM|pm)", " $1");

        // Ensure AM/PM markers are uppercase for consistency with patterns (optional)
        normalized = normalized.replaceAll("am", "AM").replaceAll("pm", "PM");

        return normalized;
    }

    /**
     * Parses a string representation of a deadline into a {@link LocalDateTime} object.
     *
     * @param deadlineStr the string representation of the date and time
     * @return the parsed {@link LocalDateTime} object
     * @throws DateTimeParseException if the input string is not in a valid date and time format
     */
    public static LocalDateTime parseDeadlineDateTime(String deadlineStr) throws DateTimeParseException {
        // Attempt to parse the string directly with existing formatters
        try {
            return parseDateTime(deadlineStr);
        } catch (DateTimeParseException e) {
            // Check if the input matches any of the date without year patterns
            for (String pattern : DATE_WITHOUT_YEAR_PATTERNS) {
                try {
                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                            .appendPattern(pattern)
                            .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 23) // Default to end of the day
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                            .toFormatter(Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(deadlineStr, formatter);
                    return LocalDateTime.of(date, LocalTime.of(23, 59)); // Use end of the day
                } catch (DateTimeParseException ignored) {
                    // Ignore and try the next pattern
                }
            }
            // If none of the patterns match, re-throw the original exception
            throw new DateTimeParseException("Invalid date or time format", deadlineStr, 0);
        }
    }

    /**
     * Attempts to parse the end time of an event task, given the end time string and the start date.
     *
     * @param endStr the string representation of the end time
     * @param startDate the start date of the event
     * @return the parsed {@link LocalDateTime} object
     */
    public static LocalDateTime tryParseEndDateTime(String endStr, LocalDate startDate) {
        for (String timePattern : TIME_PATTERNS) {
            try {
                DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
                        .appendPattern(timePattern)
                        .toFormatter(Locale.ENGLISH);
                LocalTime endTime = LocalTime.parse(endStr, timeFormatter);
                return LocalDateTime.of(startDate, endTime);
            } catch (DateTimeParseException ignored) {
            // Ignore and try the next time pattern
            }
        }
        // If none of the time patterns match, attempt parsing as full LocalDateTime
        return parseDateTime(endStr); // Fallback to using the existing parseDateTime method
    }
}
