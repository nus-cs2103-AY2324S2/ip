package nihao.handler;

import nihao.exception.IncorrectDateTimeFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeHandler() {}
    public static LocalDateTime handleInput(String input) throws IncorrectDateTimeFormatException {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(input, INPUT_FORMATTER);
            return localDateTime;
        } catch (Exception e) {
            throw new IncorrectDateTimeFormatException();
        }
    }

    public static String formatOutput(LocalDateTime input) {
        return input.format(OUTPUT_FORMATTER);
    }

    public static LocalDateTime deserialize(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, OUTPUT_FORMATTER);
    }

}
