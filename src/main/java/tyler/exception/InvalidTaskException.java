package tyler.exception;

/**
 * Represents an exception that the task index is null.
 */
public class InvalidTaskException extends TylerException {
    public InvalidTaskException() {
        super("    OHNO NO NO! You cannot mark or unmark or delete no thing!");
    }
}
