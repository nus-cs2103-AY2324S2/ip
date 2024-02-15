package checkbot.exception;

/**
 * Represents an exception where the user misses out an argument in the command.
 */
public abstract class MissingArgumentException extends CheckbotException {
    /**
     * Constructs a MissingArgumentException.
     *
     * @param arg The argument that is missing.
     */
    public MissingArgumentException(String arg) {
        super("Sorry, the argument \"" + arg + "\" is missing or empty."
                + "Please provide that by typing \"/" + arg + "\".");
    }
}
