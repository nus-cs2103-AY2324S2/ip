package squid.exceptions;

/**
 * Exception encapsulating all exceptions related to failure to parse task string.
 */
public class ParseFailException extends SquidException {
    public ParseFailException(String message) {
        super(message);
    }
}
