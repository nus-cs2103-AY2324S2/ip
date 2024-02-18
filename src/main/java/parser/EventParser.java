package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;
import task.Event;

/**
 * Represents a parser that is used to parse event tasks.
 * <p>
 * This class is used to parse event tasks from the user input.
 * </p>
 */
public class EventParser {
    /**
     * Parses the user input and returns an event task.
     * 
     * @param input The user input
     * @return The event task
     * @throws InvalidTaskFormatException If the user input is in an invalid format
     * @throws InvalidDateException       If the date in the user input is invalid
     */
    public static Event parseEvent(String input) throws InvalidTaskFormatException, InvalidDateException {
        String[] parts = input.substring("event ".length()).split(" /from | /to ", -1);
        if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
            throw new InvalidTaskFormatException(
                    "Invalid event format. Please use 'event description /from yyyy-MM-dd /to yyyy-MM-dd'.");
        }

        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new InvalidTaskFormatException("The description of an event cannot be empty.");
        }
        String from = parts[1].trim();
        String to = parts[2].trim();

        LocalDate parsedFrom;
        LocalDate parsedTo;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        parsedFrom = LocalDate.parse(from, formatter);
        parsedTo = LocalDate.parse(to, formatter);
        if (parsedFrom.isBefore(LocalDate.now())) {
            throw new InvalidDateException("The start date of an event cannot be in the past.");
        }
        if (parsedTo.isBefore(LocalDate.now())) {
            throw new InvalidDateException("The end date of an event cannot be in the past.");
        }
        if (parsedTo.isBefore(parsedFrom)) {
            throw new InvalidDateException("The end date of an event cannot be before the start date.");
        }
        return new Event(description, parsedFrom, parsedTo);
    }
}
