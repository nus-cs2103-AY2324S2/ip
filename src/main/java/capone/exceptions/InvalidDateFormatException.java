package capone.exceptions;

/**
 * Custom exception class for handling invalid date format in commands.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class InvalidDateFormatException extends CaponeException {
    /**
     * Constructs a new InvalidDateException with the specified error message.
     *
     * @param errorMessage The error message associated with the invalid date exception.
     */
    public InvalidDateFormatException(String errorMessage) {
        super(errorMessage);
    }
}
