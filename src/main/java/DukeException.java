/**
 * Class DukeException represents exceptions specific to Duke and inherits from the Exception class.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException class.
     *
     * @param message The error message.
     */
    DukeException(String message) {
        super(message);
    }
}