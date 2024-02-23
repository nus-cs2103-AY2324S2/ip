package bot;

/**
 * The Parser class is responsible for parsing user input into commands,
 * indices, descriptions, and dates.
 */
public class Parser {
    /**
     * Splits the input into two parts.
     *
     * @param input The user input.
     * @return An array of two strings.
     */
    public String[] parse(String input) {
        assert input != null : "Input is null";
        return input.split(" ", 2);
    }

    /**
     * Parses the command from the input.
     *
     * @param input The user input.
     * @return The command.
     */
    public String parseCommand(String input) {
        assert input != null : "Input is null";
        return input.split(" ", 2)[0];
    }

    /**
     * Parses the index from the input.
     *
     * @param input The user input.
     * @return The index.
     */
    public int parseIndex(String input) {
        assert input != null : "Input is null";
        int index =  Integer.parseInt(input.split(" ")[1]);
        assert index >= 0 : "Invalid index";
        return index;
    }

    /**
     * Parses the description from the input.
     *
     * @param input The user input.
     * @return The description.
     */
    public String parseDescription(String input) {
        assert input != null : "Input is null";
        return input.split(" ", 2)[1];
    }

    /**
     * Parses the deadline from the input.
     *
     * @param input The user input.
     * @return An array of two strings: the task description and the deadline.
     */
    public String[] parseDeadline(String input) {
        // Remove the deadline in the beginning, then split by /by
        return input.split("deadline ")[1].split(" /by ");
    }

    /**
     * Parses the event from the input.
     *
     * @param input The user input.
     * @return An array of three strings: the task description, the start time, and
     *         the end time.
     */
    public String[] parseEvent(String input) {
        String[] split = input.split(" /");
        String description = split[0].split("event ")[1];
        String from = split[1].split("from ")[1];
        String to = split[2].split("to ")[1];
        return new String[] { description, from, to };
    }

    /**
     * Parses the deadline from the storage input.
     *
     * @param input The storage input.
     * @return An array of four strings: the task type, the task status, the task
     *         description, and the deadline.
     */
    public String[] parseDeadlineFromStorage(String input) {
        return input.split(" \\| ", 4);
    }
}