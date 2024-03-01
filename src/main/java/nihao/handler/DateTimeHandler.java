package nihao.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nihao.exception.IncorrectDateTimeFormatException;

/**
 * Handles conversion between String and LocalDateTime objects.
 */
public class DateTimeHandler {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeHandler() {}

    /**
     * Converts a String in a specified date time format into a LocalDateTime object.
     *
     * @param input The String.
     * @return Converted LocalDateTime object.
     * @throws IncorrectDateTimeFormatException When the input String is not of the correct pattern.
     */
    public static LocalDateTime handleInput(String input) throws IncorrectDateTimeFormatException {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(input, INPUT_FORMATTER);
            return localDateTime;
        } catch (Exception e) {
            throw new IncorrectDateTimeFormatException();
        }
    }

    /**
     * Converts a LocalDateTime object into a String of a specified pattern.
     *
     * @param input The LocalDateTime object.
     * @return The converted String.
     */
    public static String formatOutput(LocalDateTime input) {
        return input.format(OUTPUT_FORMATTER);
    }

    /**
     * Converts a String into a LocalDateTime object.
     * Exclusively used for deserializing from JSON.
     *
     * @param dateTimeString The input String.
     * @return The converted LocalDateTime object.
     */
    public static LocalDateTime deserialize(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, OUTPUT_FORMATTER);
    }

}
