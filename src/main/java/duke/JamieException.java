package duke;

/**
 * Represents an exception specific to the Duke application.
 * This exception is used to indicate errors that are specific to the Duke application's logic.
 */
public class JamieException extends Exception {

    /**
     * Constructs a new JamieException with the specified detail message.
     * This constructor initializes the exception with a message detailing the cause of the error,
     * which can later be retrieved using the getMessage() method inherited from the Throwable class.
     *
     * @param message The detail message providing specific information about the exception and its cause.
     */
    public JamieException(String message) {
        super(message);
    }
}
