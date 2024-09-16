package tony;

import java.time.LocalDateTime;
import java.util.Arrays;

import tony.exceptions.BadDateException;
import tony.exceptions.InvalidTaskException;

/**
 * The Parser class provides methods for parsing user input and dates.
 */
public class Parser {

    /**
     * Parses the command from the given input string.
     *
     * @param input The user input string.
     * @return The parsed command in lowercase for case-insensitive matching.
     */
    public String parseCommand(String input) {
        assert input != null && !input.isEmpty() : "Input string should not be null or empty";
        String[] words = input.split(" ");
        return words[0].toLowerCase(); // Convert the command to lowercase for case-insensitive matching
    }

    /**
     * Parses the description from the given input string.
     *
     * @param input The user input string.
     * @return The parsed description.
     */
    public String parseDescription(String input) {
        String[] words = input.split(" ");
        return String.join(" ", Arrays.copyOfRange(words, 1, words.length));
    }

    /**
     * Parses tasks with date information from the given input string.
     *
     * @param input The user input string.
     * @return An array containing parsed task details with date information.
     */
    public String[] parseTasksWithDate(String input) {
        String[] words = input.split(" ");
        String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        String[] parts = result.split("/");
        return parts;
    }

    /**
     * Parses the update command string
     *
     * @param input The user input string.
     * @return An array containing parsed update details
     */
    public String[] parseUpdate(String input) throws InvalidTaskException {
        String[] words = input.split(" ");

        if (words[2].equals("description")) {
            String[] result = new String[2];
            String description = String.join(" ", Arrays.copyOfRange(words, 2, words.length));
            result[0] = words[1];
            result[1] = description;
            return result;
        } else if (words[2].equals("from") || words[2].equals("to") || words[2].equals("by")) {
            String[] result = new String[3];
            String date = words[3];
            result[0] = words[1];
            result[1] = words[2];
            result[2] = date;
            return result;
        } else {
            throw new InvalidTaskException("Unable to understand command");
        }
    }

    /**
     * Parses a date string and returns a LocalDateTime object.
     *
     * @param date The date string in the format "yyyy-MM-ddTHH:mm".
     * @return The LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime parseDate(String date) throws BadDateException {
        try {
            String[] dateTimeSplit = date.split("T");
            assert dateTimeSplit.length == 2 : "Invalid date format";
            String dateString = dateTimeSplit[0];
            String timeString = dateTimeSplit[1];
            String[] parts = dateString.split("-");
            assert parts.length == 3 : "Invalid date format";
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            assert year >= 0 && month >= 1 && month <= 12 && day >= 1 && day <= 31 : "Invalid date components";
            String[] timeParts = timeString.split(":");
            assert timeParts.length == 2 : "Invalid time format";
            int hour = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            assert hour >= 0 && hour <= 23 && minutes >= 0 && minutes <= 59 : "Invalid time components";
            return LocalDateTime.of(year, month, day, hour, minutes);
        } catch (Exception e) {
            throw new BadDateException();
        }
    }
}
