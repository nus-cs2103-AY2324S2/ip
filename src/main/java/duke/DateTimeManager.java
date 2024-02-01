package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class DateTimeManager {

    /**
     * Coverts the String to LocalDateTime type.
     *
     * @param timeString The time in String.
     * @return LocalDateTime.
     * @throws DukeException If none of the timeString matches the formatter.
     */
    public static LocalDateTime convertStringToLocalDateTime(String timeString) throws DukeException{
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        throw new DukeException("Invalid format for Date-Time. The format is \"yyyy-MM-dd HH:mm\".");
    }


    /**
     * Converts LocalDateTime to String.
     *
     * @param dt LocalDateTime.
     * @return the String version of DateTime.
     * @throws DukeException If none of the formatter matches the LocalDateTime.
     */
    static String convertLocalDateTimeToString(LocalDateTime dt) throws DukeException {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return dt.format(formatter);
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }
        throw new DukeException("There is an error of converting LocalDateTime to String.");
    }
}
