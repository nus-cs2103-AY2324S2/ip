package squid.exceptions;

/**
 * Exception encapsulating all exceptions related to not having enough inputs for parsing.
 */
public class NotEnoughInputsException extends IncorrectInputException {
    public NotEnoughInputsException(String message) {
        super(message);
    }
}
