package andelu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * A DateTimeManager to convert between String and LocalDateTime.
 */
public class DateTimeManager {
    private static List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

    /**
     * Coverts the String to LocalDateTime type.
     *
     * @param timeString The time in String.
     * @return LocalDateTime.
     * @throws AndeluException If none of the timeString matches the formatter.
     */
    public static LocalDateTime convertStringToLocalDateTime(String timeString) throws AndeluException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        throw new AndeluException("Invalid format for Date-Time. The format is \"yyyy-MM-dd HH:mm\".");
    }


    /**
     * Converts LocalDateTime to String.
     *
     * @param dt LocalDateTime.
     * @return the String version of DateTime.
     * @throws AndeluException If none of the formatter matches the LocalDateTime.
     */
    public static String convertLocalDateTimeToString(LocalDateTime dt) throws AndeluException {

        for (DateTimeFormatter formatter : formatters) {
            try {
                return dt.format(formatter);
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        throw new AndeluException("There is an error of converting LocalDateTime to String.");
    }
}
