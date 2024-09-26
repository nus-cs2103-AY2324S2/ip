package parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateTimeParser class provides functionality to parse date and time strings into LocalDateTime objects.
 */
public class DateTimeParser {

    /**
     * The formatter used to parse date and time strings.
     */
    protected DateTimeFormatter formatter;

    /**
     * Constructs a DateTimeParser object with a default date-time format pattern.
     * The default pattern is "dd-MM-yyyy HHmm".
     */
    public DateTimeParser() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }

    /**
     * Parses the given date-time string into a LocalDateTime object using the configured formatter.
     *
     * @param timeString A string representing date and time in the format specified by the formatter.
     * @return The parsed LocalDateTime object.
     */
    public LocalDateTime parseDateTime(String timeString) {
        return LocalDateTime.parse(timeString, formatter);
    }
}
