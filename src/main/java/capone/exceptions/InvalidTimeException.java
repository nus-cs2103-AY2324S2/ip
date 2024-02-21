package capone.exceptions;

/**
 * Custom exception class for handling invalid time format in commands.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class InvalidTimeException extends CaponeException {
    /**
     * Constructs a new InvalidTimeException with the specified error message.
     *
     * @param errorMessage The error message associated with the invalid time exception.
     */
    public InvalidTimeException(String errorMessage) {
        super(errorMessage);
    }
}
