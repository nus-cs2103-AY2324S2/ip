package utils;

import objects.Events;
import exception.InvalidEventException;

public class EventUtil {
    public static Events createEvent(String input) throws InvalidEventException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] partsFrom = input.split("/from", 2);
        String name = partsFrom[0].trim();

        String[] partsTo = partsFrom[1].split("/to", 2);
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        return new Events(name, from, to);
    };
}
