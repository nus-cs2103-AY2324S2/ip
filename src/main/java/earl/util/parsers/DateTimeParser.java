package earl.util.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class responsible for interpreting date time information.
 */
public class DateTimeParser implements Parser {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    /**
     * Returns a {@code LocalDateTime} object based on the input string.
     *
     * @param dateTime  a string representing date and time information
     * @return          a {@code LocalDateTime} object
     */
    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMAT);
    }

    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMAT);
    }
}
