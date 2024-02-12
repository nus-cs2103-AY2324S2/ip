package nollid.exceptions;

/**
 * Exception thrown when tags are missing.
 */
public class MissingTagsException extends NollidException {
    public MissingTagsException(String message) {
        super(message);
    }
}
