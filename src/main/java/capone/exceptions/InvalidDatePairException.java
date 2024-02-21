package capone.exceptions;

/**
 * Custom exception class for handling invalid LocalDateTime pairs in commands.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class InvalidDatePairException extends CaponeException {
    /**
     * Constructs a new InvalidDatePairException with the specified error message.
     *
     * @param errorMessage The error message associated with the invalid date exception.
     */
    public InvalidDatePairException(String errorMessage) {
        super(errorMessage);
    }
}
