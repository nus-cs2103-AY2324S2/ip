package checkbot.exception;

/**
 * Represents an exception where the user inputs an invalid command.
 */
public class InvalidCommandException extends CheckbotException {
    public InvalidCommandException(String cmd) {
        super("Sorry, I'm not sure what \"" + cmd + "\" means.");
    }
}
