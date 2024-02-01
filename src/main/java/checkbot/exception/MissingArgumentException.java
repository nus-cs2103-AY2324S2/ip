package checkbot.exception;

/**
 * Represents an exception where the user misses out an argument in the command.
 */
public abstract class MissingArgumentException extends CheckbotException {
    public MissingArgumentException(String arg) {
        super("Sorry, the argument \"" + arg + "\" is missing. Please provide that by typing \"/" + arg + "\".");
    }
}
