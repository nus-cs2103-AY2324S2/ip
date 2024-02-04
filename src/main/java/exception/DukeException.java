package exception;

/**
 * DukeException is the base exception class for handling Duke-related errors.
 * It extends the standard Java Exception class and includes a custom error message.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super("ERROR! " + message);
    }
}
