package duke.utils;

import static duke.constants.Constant.DATE_FORMATTER;
import static duke.constants.Constant.DATE_TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Utility methods for common tasks.
 */
public class Utils {
    /**
     * Removes extra spaces from the input string.
     *
     * @param input the input string
     * @return the string with extra spaces removed
     */
    public static String removeExtraSpaces(String input) {
        return input.trim();
    }

    /**
     * Converts time string to LocalDateTime object, accepted date time format is yyyy-MM-dd HHmm
     * @param timeStr for convert to LocalDateTime
     * @return time in LocalDateTime object
     */
    public static LocalDateTime convertToLocalDateTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
    }

    /**
     * Converts time string to LocalDate object, accepted date format is yyyy-MM-dd
     * @param timeStr for convert to LocalDate
     * @return time in LocalDate object
     */
    public static LocalDate convertToLocalDate(String timeStr) {
        return LocalDate.parse(timeStr, DATE_FORMATTER);
    }
}
