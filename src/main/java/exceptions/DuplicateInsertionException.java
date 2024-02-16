package exceptions;

/**
 * An exception thrown when attempting to insert a duplicate task.
 * Extends the {@link LuluException} class, providing a specific message for duplicate task detection.
 */
public class DuplicateInsertionException extends LuluException {
    public DuplicateInsertionException() {
        super("Duplicate task detected");
    }
}
