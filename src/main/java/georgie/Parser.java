package georgie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and performs specific parsing operations.
 */
public class Parser {

    /**
     * Parses the user input into an array of strings.
     *
     * @param userInput The user input to be parsed.
     * @return An array of strings.
     */
    public static String[] parse(String userInput) {
        return userInput.split(" ", 2);
    }

    /**
     * Parses the date from the given date string.
     *
     * @param dateString The date string to parse.
     * @return The parsed LocalDate.
     * @throws GeorgieException If there is an issue parsing the date.
     */
    public static LocalDate parseDate(String dateString) throws GeorgieException {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new GeorgieException("Error parsing date: " + e.getMessage());
        }
    }

    /**
     * Parses the deadline command to extract description and due by details.
     *
     * @param userInput The user input for the deadline command.
     * @return An array containing description and due by details.
     */
    public static String[] parseDeadline(String userInput) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            return new String[]{parts[0], ""};
        }

        String[] descriptionAndDueBy = parts[1].split(" /by ", 2);
        if (descriptionAndDueBy.length < 2) {
            return new String[]{parts[0], descriptionAndDueBy[0]};
        }

        return new String[]{descriptionAndDueBy[0], descriptionAndDueBy[1]};
    }

    /**
     * Parses the event command to extract description, start, and end details.
     *
     * @param userInput The user input for the event command.
     * @return An array containing description, start, and end details.
     */
    public static String[] parseEvent(String userInput) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            return new String[]{parts[0], ""};
        }

        String[] descriptionAndDetails = parts[1].split(" /from | /to ", 3);

        if (descriptionAndDetails.length < 3) {
            return new String[]{parts[0], descriptionAndDetails[0]};
        }

        return new String[]{descriptionAndDetails[0], descriptionAndDetails[1], descriptionAndDetails[2]};
    }
}
