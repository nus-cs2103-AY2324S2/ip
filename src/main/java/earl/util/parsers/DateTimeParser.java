package earl.util.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class responsible for unified interpretation of date time information.
 */
public class DateTimeParser implements Parser<LocalDateTime> {

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

    /**
     * Returns date and time information in printable format.
     *
     * @param dateTime  a {@code LocalDateTime} object to be printed
     * @return          a string of date and time information
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMAT);
    }
}
