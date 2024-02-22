package duke;
/**
 * DukeException is a custom exception class for errors.
 * It extends the Exception class and prints a message for each error case.
 */
public class DukeException extends Exception {
    /**
     * sends the error message according to the error type
     *
     * @param message The error message about the exception
     */
    public DukeException(String message) {
        super(message);
    }
}
