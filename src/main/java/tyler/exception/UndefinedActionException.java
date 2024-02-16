package tyler.exception;

/**
 * Represents an exception that the action is not defined.
 */
public class UndefinedActionException extends TylerException {
    public UndefinedActionException() {
        super("    OHNO NO NO! I don't know what you mean?");
    }
}
