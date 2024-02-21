package chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser that parses the input from the user.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public Parser() {
    }
    /**
     * Parses the description from the input.
     * @param input The input from the user.
     * @return The description of the task.
     */
    public String parseDescription(String input) throws AlfredException {
        assert input != null : "Input should not be null";
        assert !input.isBlank() : "Input should not be blank";
        if (input.indexOf(" ") == -1) {
            throw new AlfredException("Sorry Master Bruce. Please enter a description.");
        }
        return input.substring(input.indexOf(" ") + 1).trim();
    }

    /**
     * Parses the index from the input.
     * @param index The index from the user.
     * @return The index of the task.
     */
    public int parseIndex(String index) throws AlfredException {
        assert index != null : "Index should not be null";
        assert !index.isBlank() : "Index should not be blank";
        int extractedIdx = -1;
        try {
            if (index.isEmpty()) {
                throw new AlfredException("Please enter a number after the command.");
            }
            extractedIdx = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new AlfredException("Sorry Master Bruce. Please enter a valid number.");
        }
        return extractedIdx;
    }
    /**
     * Parses the date-time from the input.
     * @param input The input from the user.
     * @return The date-time of the task.
     */
    public LocalDateTime parseDateTime(String input) throws AlfredException {
        assert input != null : "Input should not be null";
        assert !input.isBlank() : "Input should not be blank";
        if (input.indexOf(" ") == -1) {
            throw new AlfredException("Sorry Master Bruce. Please enter a date and time.");
        }
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }
}
