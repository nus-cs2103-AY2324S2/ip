package duke.exceptions;

/**
 * Exception thrown when the instruction format is wrong.
 */
public class WrongFormatException extends BaseException{
    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public WrongFormatException(String message) {
        super("!!!ERROR: Incorrect instruction format. The correct format is: " + message);
    }
}
