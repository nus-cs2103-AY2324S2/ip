package nihao.exception;

/**
 * Represents an Exception thrown when a command gets an illegal set of arguments.
 */
public class IllegalArgumentException extends Exception {
    /**
     * Constructor when the expected number of arguments is provided.
     *
     * @param commandName The name of the command.
     * @param expected The expected number of arguments.
     */
    public IllegalArgumentException(String commandName, int expected) {
        super("IllegalArgumentException: " + commandName + " expects " + expected + " arguments.");
    }

    /**
     * Constructor when only a general piece of error information is provided.
     */
    public IllegalArgumentException(String errorMessage) {
        super("IllegalArgumentException: " + errorMessage + ".");
    }
}
