package exceptions;

/**
 * An exception indicating an invalid attempt to update the status of a task to a value that has already been set.
 * This exception is specific to the lulu.Lulu application and is a subclass of {@link LuluException}.
 */
public class InvalidStatusUpdateException extends LuluException {
    public InvalidStatusUpdateException() {
        super("Status was already updated to desired value");
    }
}
