package datuk;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class processes user input.
 */

public class Parser {

    public Parser() {
    }

    /**
     * Returns the first word used to control the program flow in Duke.
     * <p>
     * This method separates the first word from the full instruction given by the user.
     *
     * @param cmd the full command given by the user.
     * @return the first word in the string.
     */
    public String parseCmd(String cmd) {
        if (cmd.equals("bye") || cmd.equals("list")){
            return cmd;
        }

        String[] split = cmd.split(" ");
        return split[0];
    }

    /**
     * Returns an array of strings for the delete command usable by TaskList.
     * <p>
     * The method processes the delete command.
     *
     * @param cmd the full instruction.
     * @return an array of strings separated by function and index.
     * @throws DukeException when given wrong number of parameters.
     */
    public String[] parseDelete(String cmd) throws DukeException {
        String[] str = cmd.split(" ");

        if (str.length < 2) {
            throw new DukeException("Missing params for delete!");
        } else if (str.length > 2) {
            throw new DukeException("Too many params for delete!");
        }

        try {
            Integer.parseInt(str[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number given.");
        }

        return str;
    }

    /**
     * Returns an array of strings for the mark/unmark command usable by TaskList.
     * <p>
     * This method will set the task to be complete or incomplete according to the user instruction.
     *
     * @param cmd the mark/unmark command.
     * @return an array of strings separated by function and index.
     * @throws DukeException when given incorrect parameter type or incorrect number of parameters.
     */
    public String[] parseMark(String cmd) throws DukeException {
        String[] str = cmd.split(" ");

        if (str.length != 2) {
            throw new DukeException("Incorrect number of params for mark/unmark");
        }

        try {
            Integer.parseInt(str[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number given.");
        }

        return str;
    }

    /**
     * Returns an array of strings of the commands usable by Todo, deadline or event.
     * <p>
     * This method processes all Add commands (Todo, deadline, event).
     *
     * @param cmd add command.
     * @return an array of strings separated by function and parameters.
     * @throws DukeException when given incorrect number of parameters.
     */
    public String[] parseAdd(String cmd) throws DukeException {
        String[] str = cmd.split(" ", 2);

        if (str[0].equals("todo")) {
            return parseTodo(str);
        } else if (str[0].equals("deadline")) {
            return parseDeadline(str);
        } else {
          return parseEvent(str);
        }

    }

    private String[] parseTodo(String[] str) throws DukeException {
        String desc;
        if (str.length != 2) {
            throw new DukeException("Missing params for todo!");
        }
        desc = str[1].replaceAll("\\s", "");
        return new String[] { str[0], desc };
    }

    private String[] parseDeadline(String[] str) throws DukeException {
        String desc;
        LocalDate by;

        if (str.length != 2) {
            throw new DukeException("Missing params for deadline!");
        }

        String[] temp = str[1].split("/by");

        if (temp.length != 2) {
            throw new DukeException("Missing deadline for deadline!");
        }

        desc = temp[0].replaceAll("\\s", "");
        by = validDate(temp[1].replaceAll("\\s", ""));
        return new String[] { str[0], desc, by.toString() };
    }

    private String[] parseEvent(String[] str) throws DukeException {
        String desc, from, to;

        if (str.length != 2) {
            throw new DukeException("Missing params for event!");
        }

        String[] temp = str[1].split("/from");

        if (temp.length != 2) {
            throw new DukeException("Missing [from] and [to] for event!");
        }

        desc = str[1].split("/from")[0].replaceAll("\\s", "");

        String[] temp2 = str[1].split("/from")[1].split("/to");

        if (temp2.length != 2) {
            throw new DukeException("Missing [to] for event!");
        }

        from = temp2[0].replaceAll("\\s", "");
        to = temp2[1].replaceAll("\\s", "");

        return new String[] { str[0], desc, from, to };
    }

    /**
     * Returns an Array of strings usable by find method.
     * <p>
     * This method matches the keyword to the descriptions found in every item in the TaskList.
     *
     * @param cmd find command.
     * @return str an array of strings separated by find and its keyword.
     * @throws DukeException when given incorrect number of parameters.
     */
    public String[] parseFind(String cmd) throws DukeException {
        String[] str = cmd.split(" ", 2);

        if (str.length != 2) {
            throw new DukeException("Missing params for find");
        }

        return str;
    }

    /**
     * Returns LocalDate object that can be used by deadline.
     * <p>
     * This method will validate that the input string is in the correct date format.
     *
     * @param str A string that might be a date.
     * @return a Local date Object.
     * @throws DukeException when the string format is not a valid date.
     */
    public LocalDate validDate(String str) throws DukeException {
        LocalDate ld;

        try {
            ld = LocalDate.parse(str);
        } catch (DateTimeParseException dt) {
            throw new DukeException("Invalid date format. Use yyyy-MM-dd format.");
        }

        return ld;
    }

}
