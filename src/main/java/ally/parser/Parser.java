package ally.parser;

/**
 * Abstraction of String parsing.
 * Receives String input from user, and parses to the accurate task.
 */
public class Parser {

    /**
     * Represent all possible commands made by user to Duke
     */
    public enum Command {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, ARCHIVE, UNKNOWN
    }

    /**
     * Parses Command from user string.
     * @param input user input String
     * @return Command
     */
    public static Command parseCommand(String input) {
        String[] words = input.split(" ", 2);
        String firstWord = words.length > 0 ? words[0] : "";
        try {
            return Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * Parses user string to get Task Details.
     * @param input user input
     * @return String string containing task details
     */
    public static String parseTaskDetail(String input) {
        String[] words = input.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
}
