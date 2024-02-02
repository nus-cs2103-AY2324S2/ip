package squid.exceptions;

/**
 * Exception encapsulating all exceptions related to Squid.
 */
public abstract class SquidException extends Exception {
    public SquidException(String message) {
        super(message);
    }
}
