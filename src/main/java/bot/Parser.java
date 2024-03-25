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
    public String[] parse(String input) throws DukeException {
        assert input != null : "Input is null";
        if (input.isBlank()) {
            throw new DukeException("Please enter a command.");
        }
        return input.split(" ", 2);
    }

    /**
     * Parses the command from the input.
     *
     * @param input The user input.
     * @return The command.
     */
    public String parseCommand(String input) throws DukeException {
        assert input != null : "Input is null";

        try {
            return input.split(" ", 2)[0];
        } catch (Exception e) {
            throw new DukeException("Invalid Format for Command.");
        }

    }

    /**
     * Parses the index from the input.
     *
     * @param input The user input.
     * @return The index.
     */
    public int parseIndex(String input) throws DukeException {
        assert input != null : "Input is null";
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (input.split(" ").length < 2) {
                throw new DukeException("Please enter an index.");
            }
            assert index >= 0 : "Invalid index";
            return index;
        } catch (Exception e) {
            throw new DukeException("Index not parsed or index not an integer.");
        }
    }

    /**
     * Parses the description from the input.
     *
     * @param input The user input.
     * @return The description.
     */
    public String parseDescription(String input) throws DukeException {
        assert input != null : "Input is null";
        try {
            return input.split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("Invalid Format for Description.");
        }
    }

    /**
     * Parses the deadline from the input.
     *
     * @param input The user input.
     * @return An array of two strings: the task description and the deadline.
     */
    public String[] parseDeadline(String input) throws DukeException {
        try {
            // Remove the deadline in the beginning, then split by /by
            return input.split("deadline ")[1].split(" /by ");
        } catch (Exception e) {
            throw new DukeException("Invalid Format for Deadline.");
        }
    }

    /**
     * Parses the event from the input.
     *
     * @param input The user input.
     * @return An array of three strings: the task description, the start time, and
     *         the end time.
     */
    public String[] parseEvent(String input) throws DukeException {
        try {
            System.out.println("input");
            System.out.println(input);
            String[] split = input.split(" /from ");
            String beforeFrom = split[0];
            String afterFrom = split[1];
            String description = beforeFrom.split("event ")[1];
            String[] split2 = afterFrom.split(" /to ");
            String from = split2[0];
            String to = split2[1];
            return new String[] { description, from, to };
        } catch (Exception e) {
            throw new DukeException("Invalid Format for Event.");
        }
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