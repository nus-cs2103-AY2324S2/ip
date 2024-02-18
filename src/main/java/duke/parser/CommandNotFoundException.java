package duke.parser;

/**
 * Represents exception thrown when input command does not match with existing commands.
 */
public class CommandNotFoundException extends InputException {
    public CommandNotFoundException(String command) {
        super("Command not found: " + command);
    }
}