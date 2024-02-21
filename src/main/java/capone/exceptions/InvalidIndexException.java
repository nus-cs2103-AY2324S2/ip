package capone.exceptions;

/**
 * Custom exception class for handling invalid indices in commands.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class InvalidIndexException extends CaponeException {
    /**
     * Constructs a new InvalidIndexException with the specified error message.
     *
     * @param errorMessage The error message associated with the invalid index exception.
     */
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}
