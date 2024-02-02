package utils;

import exception.InvalidCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputUtil {
    public static String getCommandType(String input) {
        return input.split(" ", 2)[0];
    }

    public static LocalDateTime convertToDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        return LocalDateTime.parse(dateString, formatter);
    }
}
