package duke.parser;

/**
 * Abstraction of String parsing.
 * Receives String input from user, and parses to the accurate task.
 */
public class Parser {

    public enum Command {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static Command parseCommand(String input) {
        String[] words = input.split(" ", 2);
        String firstWord = words.length > 0 ? words[0] : "";

        try {
            return Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    public static String parseTaskDetail(String input) {
        String[] words = input.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
}
