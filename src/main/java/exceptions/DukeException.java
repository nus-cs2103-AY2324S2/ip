package exceptions;

/**
 * Custom exception class for handling exceptions specific to the Duke application.
 * This class extends the standard Java Exception class and provides additional
 * context or information about errors specific to the Duke application's operations.
 *
 * @author Muhammad Rizki Bayuaji
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     *
     * @param message The detail message which provides more information about the exception.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public DukeException(String message) {
        super(message);
    }
}
