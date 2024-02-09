package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String[] parse(String userInput) {
        return userInput.split(" ", 2);
    }

    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Error parsing date: " + e.getMessage());
        }
    }

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

    public static String[] parseEvent(String userInput) {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            return new String[]{parts[0], ""};
        }

        String[] descriptionAndDetails = parts[1].split(" /from | /to ", 3);

        if (descriptionAndDetails.length < 2) {
            return new String[]{parts[0], descriptionAndDetails[0]};
        }

        return new String[]{descriptionAndDetails[0], descriptionAndDetails[1], descriptionAndDetails[2]};
    }
}
