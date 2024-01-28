package duke.codec;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * The `TimeProcessor` class provides utility methods for converting between `LocalDateTime` and formatted strings.
 */
public class TimeProcessor {
    private static final String dateFormat = "dd-MM-yyyy";
    private static final String timeFormat = "HH:mm";

    private static final DateTimeFormatter fmt = new DateTimeFormatterBuilder()
        .appendPattern(dateFormat)
        .optionalStart()
        .appendLiteral(' ')
        .appendPattern(timeFormat)
        .optionalEnd()
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .toFormatter();


    public static String toString(LocalDateTime value) throws DateTimeException {
        return fmt.format(value);
    }

    public static LocalDateTime fromString(String value) throws DateTimeParseException {
        return LocalDateTime.parse(value, fmt);
    }

}
