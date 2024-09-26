package tyler.exception;

/**
 * Represents an exception that the taskName is null.
 */
public class EmptyNameException extends TylerException {
    public EmptyNameException() {
        super("    OHNO NO NO! The name of the task or event cannot be empty!");
    }
}
