package capone.exceptions;

/**
 * Custom exception class for handling insufficient arguments in commands.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class InsufficientArgumentException extends CaponeException {
    /**
     * Constructs a new InsufficientArgumentException with the specified error message.
     *
     * @param errorMessage The error message associated with the insufficient argument exception.
     */
    public InsufficientArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
