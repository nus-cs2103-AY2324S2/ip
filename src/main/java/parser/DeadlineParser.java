package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;
import task.Deadline;

/**
 * Represents a parser that is used to parse deadline tasks.
 * <p>
 * This class is used to parse deadline tasks from the user input.
 * </p>
 */
public class DeadlineParser {
    /**
     * Parses the user input and returns a deadline task.
     * 
     * @param input The user input
     * @return The deadline task
     * @throws InvalidTaskFormatException If the user input is in an invalid format
     * @throws InvalidDateException       If the date in the user input is invalid
     */
    public static Deadline parseDeadline(String input) throws InvalidTaskFormatException, InvalidDateException {
        String[] parts = input.substring("deadline ".length()).split(" /by ", -1);
        if (parts.length != 2) {
            throw new InvalidTaskFormatException(
                    "Invalid deadline format. Please use 'deadline description /by yyyy-MM-dd'.");
        }
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new InvalidTaskFormatException("The description of a deadline cannot be empty.");
        }
        LocalDate parsedBy;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            parsedBy = LocalDate.parse(parts[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Unable to parse date. Ensure the date is in 'yyyy-MM-dd' format.");
        }

        if (parsedBy.isBefore(LocalDate.now())) {
            throw new InvalidDateException("The deadline date cannot be in the past.");
        }
        return new Deadline(description, parsedBy);
    }
}
