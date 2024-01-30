package utils;

import objects.Deadlines;
import exception.InvalidDeadlineException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static Deadlines createDeadline(String input) throws InvalidDeadlineException {
        if (!input.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        String[] parts = input.split("/by", 2);
        String name = parts[0].trim();
        String byString = parts.length > 1 ? parts[1].trim() : "";
        LocalDateTime byDateTime = parseDeadlineDateTime(byString);

        return new Deadlines(name, byDateTime);
    };

    private static LocalDateTime parseDeadlineDateTime(String dateTimeString) throws InvalidDeadlineException {
        try {
            return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
        } catch (Exception e) {
//            throw new InvalidDeadlineException("Invalid date/time format. Please use d/M/yyyy HHmm (2/12/2019 1800).");
            throw new InvalidDeadlineException();
        }
    }
}
