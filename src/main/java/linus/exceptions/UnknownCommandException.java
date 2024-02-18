package linus.exceptions;

/**
 * Thrown when trying to use an unknown command
 */
public class UnknownCommandException extends LinusCeption {
    public UnknownCommandException(String error) {
        super(error);
    }
}
