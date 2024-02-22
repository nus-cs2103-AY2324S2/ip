package exceptions;

/**
 * Represents an exception that is thrown when an operation in the KaiYap application
 * attempts to add or mark an entity that already exists in its intended context.
 */
public class AlreadyExistsException extends KaiYapException {
    public AlreadyExistsException(String errorMsg) {
        super(errorMsg);
    }
}
