package duke.exceptions;

/**
 * Exception thrown when the index is out of bound.
 */
public class OutOfBoundException extends BaseException{
    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public OutOfBoundException() {
        super("Error: Index out of bound.");
    }


}
