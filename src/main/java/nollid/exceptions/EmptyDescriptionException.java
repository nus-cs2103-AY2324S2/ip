package nollid.exceptions;

/**
 * Exception thrown when an empty description is encountered.
 */
public class EmptyDescriptionException extends NollidException {
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
