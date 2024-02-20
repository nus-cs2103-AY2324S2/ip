package duke.exceptions;

/**
 * Exception thrown when the command body is empty.
 */
public class EmptyBodyException extends BaseException {
    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public EmptyBodyException() {
        super("!!!ERROR: Please specify the content.");
    }
}
