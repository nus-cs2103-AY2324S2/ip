package utils;

import objects.Events;
import exception.InvalidEventException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static Events createEvent(String input) throws InvalidEventException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] partsFrom = input.split("/from", 2);
        String name = partsFrom[0].trim();

        String[] partsTo = partsFrom[1].split("/to", 2);
        String fromString = partsTo[0].trim();
        String toString = partsTo[1].trim();

        LocalDateTime fromDateTime = parseEventDateTime(fromString);
        LocalDateTime toDateTime = parseEventDateTime(toString);

        return new Events(name, fromDateTime, toDateTime);
    }

    private static LocalDateTime parseEventDateTime(String dateTimeString) throws InvalidEventException {
        try {
            return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new InvalidEventException();
        }
    }
}
