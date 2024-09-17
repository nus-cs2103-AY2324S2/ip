package squid.exceptions;

/**
 * Exception encapsulating all exceptions related to not having enough dates in inputs.
 */
public class NotEnoughDatesException extends NotEnoughInputsException {
    public NotEnoughDatesException(String message) {
        super(message);
    }
}
