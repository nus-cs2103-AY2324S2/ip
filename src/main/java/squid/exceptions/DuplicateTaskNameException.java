package squid.exceptions;

/**
 * Exception encapsulating all exceptions related to duplicate task names.
 */
public class DuplicateTaskNameException extends IncorrectInputException {

    public DuplicateTaskNameException(String message) {
        super(message);
    }
}
