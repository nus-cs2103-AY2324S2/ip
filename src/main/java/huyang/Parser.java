package huyang;

/**
 * Parser class responsible for parsing user input commands.
 */
public class Parser {
    /**
     * Enumeration representing the possible command types.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, UNKNOWN;
    }

    /**
     * Parses a user input command and determines its CommandType.
     *
     * @param input The user input command string.
     * @return The CommandType corresponding to the parsed input.
     */
    public CommandType parseCommand(String input) {
        input = input.toLowerCase();
        if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        } else if (input.equals("bye")) {
            return CommandType.BYE;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}
