package duke.exceptions;

/**
 * Exception thrown when the index is out of bound.
 */
public class InvalidNumberException extends BaseException{
    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public InvalidNumberException() {
        super("Error: Please enter a valid number.");
    }
}
