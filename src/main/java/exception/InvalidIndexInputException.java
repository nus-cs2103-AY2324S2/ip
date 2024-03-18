package exception;

/**
 * Custom exception class to represent an invalid index input.
 * Extends the DukeException class.
 */
public class InvalidIndexInputException extends DukeException {

    /**
     * Constructs an InvalidIndexInputException with a default error message.
     */
    public InvalidIndexInputException() {
        super("Only enter integers!");
    }
}
