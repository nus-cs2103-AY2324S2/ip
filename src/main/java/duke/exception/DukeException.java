package duke.exception;

/**
 * Class duke.exception.DukeException represents exceptions specific to duke.main.Duke and inherits from the Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructor for duke.exception.DukeException class.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}