import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class TimeProcessor {
    static String dateFormat = "dd-MM-yyyy";
    static String timeFormat = "HH:mm";

    static DateTimeFormatter fmt = new DateTimeFormatterBuilder()
    .appendPattern(dateFormat)
    .optionalStart()
    .appendLiteral(' ')
    .appendPattern(timeFormat)
    .optionalEnd()
    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
    .toFormatter();

    

    public static String toString(LocalDateTime value) throws DateTimeException{
        return fmt.format(value);
    }

    public static LocalDateTime fromString(String value) throws DateTimeParseException{
        return LocalDateTime.parse(value, fmt);
    }

    

}