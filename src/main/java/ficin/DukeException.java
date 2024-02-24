package ficin;

/**
 * Represents application-specific exceptions in the Duke application.
 * This class extends the standard Java Exception class to provide custom error
 * handling capabilities tailored to the Duke application's needs.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     *
     * @param message The error message associated with the exception. This message provides
     *                more details about the cause of the exception and is intended to be
     *                meaningful to humans.
     */
    public DukeException(String message) {
        super(message);
    }
}
