package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {
    final static private String FORMAT = "d/M/yyyy HHmm";

    public static boolean isValidDateTime(String dateTime) {
        dateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        try {
            LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidDateTime(String fromDateTime, String toDateTime) {
        fromDateTime = fromDateTime.trim();
        toDateTime = fromDateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        try {
            LocalDateTime from = LocalDateTime.parse(fromDateTime, formatter);
            LocalDateTime to = LocalDateTime.parse(toDateTime, formatter);
            return (from.isBefore(to) || from.isEqual(to)) ? true : false;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String outputFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

    public static String inputFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT));
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
