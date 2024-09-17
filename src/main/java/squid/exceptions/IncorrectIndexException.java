package squid.exceptions;

/**
 * Exception encapsulating all exceptions related to an incorrect task index.
 */
public class IncorrectIndexException extends IncorrectInputException {
    public IncorrectIndexException(String message) {
        super(message);
    }
}
