package duke;

/**
 * Represents an exception specific to the Duke application.
 * This exception is thrown to indicate that an application-specific
 * error has occurred, typically due to incorrect user input or invalid
 * operations in the context of the Duke application.
 */
public class JamieException extends Exception {

    /**
     * Constructs a new JamieException with the specified detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     *
     * @param message The detail message which provides specific information about the exception.
     */
    public JamieException(String message) {
        super(message);
    }
}
