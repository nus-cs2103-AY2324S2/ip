package capone.exceptions;

/**
 * Custom exception class for handling invalid commands.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class InvalidCommandException extends CaponeException {
    /**
     * Constructs a new InvalidCommandException with the specified error message.
     *
     * @param errorMessage The error message associated with the invalid command exception.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
