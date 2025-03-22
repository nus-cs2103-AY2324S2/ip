package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * InputUtil class provides utility methods for processing input.
 */
public class InputUtil {

    /**
     * Extracts the command type from the input string.
     *
     * @param input The input string containing the command.
     * @return The command type extracted from the input.
     */
    public static String getCommandType(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Converts a date and time string to a LocalDateTime object.
     *
     * @param dateString The date and time string in the format "d/M/yyyy HHmm".
     * @return The LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime convertToDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        return LocalDateTime.parse(dateString, formatter);
    }
}
