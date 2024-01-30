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
     * Parses the command word from the user input.
     *
     * @param input The user input string.
     * @return The parsed command as a Command enum.
     */
    public static Command parseCommand(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        return Command.fromString(commandWord);
    }

    /**
     * Parses the task index from the user input.
     *
     * @param input The user input string.
     * @return The parsed task index as an integer.
     * @throws NumberFormatException if the task index is not a valid number.
     * @throws IllegalArgumentException if no task index is provided in the input.
     */
    public static int parseTaskIndex(String input) throws NumberFormatException {
        String[] words = input.split(" ", 2);
        if (words.length < 2) {
            throw new IllegalArgumentException("No task index provided.");
        }
        return Integer.parseInt(words[1]) - 1; // Subtract 1 to convert to zero-based index
    }

    /**
     * Parses the task description from the user input.
     *
     * @param input The user input string.
     * @return The task description.
     * @throws TodoLeftBlank if the description part of the input is empty.
     */
    public static String parseDescription(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TodoLeftBlank("Looks like you left the description of the todo empty. This isn't allowed!");
        }
        return parts[1].trim();
    }

    /**
     * Parses the deadline task from the user input.
     * 
     * @param input The user input string.
     * @return An Object array where the first element is the task description and the second element is the deadline as a LocalDateTime.
     * @throws Exception if the deadline format is incorrect or if the 'deadline' keyword is misspelled.
     */
    public static Object[] parseDeadline(String input) throws Exception {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Deadline time not provided.");
        }
        if (!parts[0].split(" ")[0].equals("deadline")) {
            throw new Exception("Looks like you spelt deadline wrong. Please try again!");
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
     * 
     * @param input The user input string.
     * @return A String array where the first element is the event title, the second element is the start time, and the third element is the end time.
     */
    public static String[] parseEvent(String input) {
        String[] parts = input.substring(6).split("/");
        String title = parts[0];
        String from = "";
        String to = "";
    
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].split(" ")[0].equals("from")) {
                from = parts[i].substring(5);
            }
            if (parts[i].split(" ")[0].equals("to")) {
                to = parts[i].substring(3);
            }
        }
        return new String[]{title, from, to};
    }
}