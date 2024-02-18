package exceptions;

/**
 * The exception thrown when user inputs something that the program does not support.
 */
public class DukeException extends Exception {
    /**
     * DukeException class constructor.
     * @param message The exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
