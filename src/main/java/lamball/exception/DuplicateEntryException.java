package lamball.exception;

/**
 * Exception class for duplicate entries in the list.
 */
public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String msg) {
        super(msg);
    }
}
