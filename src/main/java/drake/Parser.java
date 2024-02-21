package drake;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user input into various components such as commands, task indices, descriptions, and deadlines.
 */
public class Parser {

    /**
     * Parses an event task from the user input. It assumes the input contains
     *     the event details after the 'event' keyword.
     *
     * @param input The user input string that contains the event details.
     * @return A String array with three elements: the event title, the start time, and the end time.
     * @throws IllegalArgumentException If the input does not contain valid 'from' and 'to' times for the event.
     */
    public static int parseTaskIndex(String input) throws Exception {
        assert input != null && input.contains(" ") : "Invalid input format for parsing task index.";
        String[] words = input.split(" ", 2);
        if (words.length < 2) {
            throw new IllegalArgumentException("No task index provided.");
        }
        try {
            return Integer.parseInt(words[1]) - 1; // Subtract 1 to convert to zero-based index
        } catch (Exception e) {
            throw new Exception("Enter a valid integer to select task!");
        }
    }

    /**
     * Parses the task description from the user input.
     *
     * @param input The user input string.
     * @return The task description.
     * @throws TodoLeftBlank if the description part of the input is empty.
     */
    public static String parseDescription(String input) {
        assert input != null && input.contains(" ") : "Invalid input format for parsing description.";
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TodoLeftBlank("Looks like you left the description of the todo empty. This isn't allowed!");
        }
        return parts[1].trim();
    }

    /**
     * Parses the deadline task from the user input.
     * @return An Object array where the first element is the task description and the second element
     *     is the deadline as a LocalDateTime.
     * @throws Exception if the deadline format is incorrect or if the 'deadline' keyword is misspelled.
     */
    public static Object[] parseDeadline(String input) throws Exception {
        assert input != null && input.contains(" /by ") : "Input must contain '/by' to specify the deadline.";
        String[] parts = input.split(" /by ");
        if (parts.length == 1) {
            throw new IllegalArgumentException("Looks like you didn't input the 'by' sub-command. "
                    + "This isn't allowed!");
        }

        String description = parts[0].substring(parts[0].indexOf(' ') + 1).trim();
        LocalDate date;
        LocalDateTime by;
        try {
            date = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            by = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new Exception("Date is of the wrong format!");
        }
        return new Object[]{description, by};

    }

    /**
     * Parses an event task from the user input.
     * @param input The user input string.
     * @return A String array where the first element is the event title,
     *     the second element is the start time, and the third element is the end time.
     */
    public static Object[] parseEvent(String input) throws Exception {
        if (input.trim().equals("event")) {
            throw new IllegalArgumentException("Looks like you left the description of the event empty. "
                    + "This isn't allowed!");
        }
        String[] parts = input.substring(6).split("/");

        if (parts.length != 3) {
            throw new Exception("Please add both 'from' and 'to' sub-commands!");
        }

        String title = parts[0];
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].split(" ")[0].equals("from")) {
                try {
                    LocalDate fromD = LocalDate.parse(parts[i].substring(5).trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    System.out.println(fromD);
                    fromDate = fromD.atStartOfDay();
                    System.out.println(fromDate);
                } catch (DateTimeParseException e) {
                    throw new Exception("From Date is of the wrong format! Make sure it is of form 'yyyy-mm-dd'.");
                }
            } else if (parts[i].split(" ")[0].equals("to")) {
                try {
                    toDate = LocalDate.parse(parts[i].substring(3).trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
                } catch (DateTimeParseException e) {
                    throw new Exception("To Date is of the wrong format! Make sure it is of form 'yyyy-mm-dd'.");
                }
            } else {
                throw new Exception("Invalid or incomplete sub-command. "
                        + "Include both 'from' and 'to' sub-commands only!");
            }
        }
        return new Object[]{title, fromDate, toDate};
    }

    /**
     * Parses the search keyword from the user input. It assumes the input contains
     *     the keyword after the 'find' command.
     *
     * @param input The user input string that contains the search keyword.
     * @return The search keyword.
     * @throws IllegalArgumentException If the keyword for search is missing or empty.
     */
    public static String parseKeyword(String input) {
        assert input != null && input.contains(" ") : "Invalid input format for parsing search keyword.";
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword for search is missing.");
        }
        return parts[1].trim();
    }

    /**
     * Parses the name and description from the user input.
     *
     * @param input The user input string that contains the contact details.
     * @return The name and description as string's in a string array.
     * @throws IllegalArgumentException If the name or description is missing or empty.
     */
    public static String[] parseContactAdd(String input) throws IllegalArgumentException {
        assert input != null && input.contains(" ") : "Invalid input format for parsing.";
        String[] parts = input.split(" ", 3);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Looks like you left the name of the contact empty."
                    + "This isn't allowed!");
        }

        String name = parts[1].trim();
        String description = parts.length > 2 ? parts[2].trim() : "";
        return new String[]{name, description};
    }

}
