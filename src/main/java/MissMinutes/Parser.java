package MissMinutes;

/**
 * Robust Parser class to parse the user input
 */
public class Parser {
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }

    /**
     * Given the user input string, parses into the respective CommandType enum.
     * Trims trailing and leading whitespace, sets lowercase to reliably parse the user input.
     *
     * @param input The input string from the user
     * @return The respective CommandType enum
     */
    public static CommandType parseCommand(String input) {
        String trimmed = input.trim();
        String[] split = trimmed.split(" ", 2);
        String command = split[0].toLowerCase();

        switch (command) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "delete":
                return CommandType.DELETE;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            default:
                return CommandType.UNKNOWN;
        }
    }

}
