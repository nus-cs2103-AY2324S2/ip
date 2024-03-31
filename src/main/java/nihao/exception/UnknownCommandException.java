package nihao.exception;

/**
 * Represents an Exception thrown when the command provided is not recognized.
 */
public class UnknownCommandException extends Exception {
    /**
     * Class constructor specifying the command name.
     */
    public UnknownCommandException(String commandName) {
        super("UnknownCommandException: " + "'" + commandName + "'"
                + " is unknown.");
    }

}
