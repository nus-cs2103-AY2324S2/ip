package duke.exceptions;

/**
 * Exception thrown when the instruction keyword is unknown.
 */
public class InvalidKeyException extends BaseException {
    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public InvalidKeyException() {
        super("!!!ERROR: Sorry we can't recognize your instruction, please use valid one. "
            + "(Note that the instruction is case-sensitive)");
    }
}
