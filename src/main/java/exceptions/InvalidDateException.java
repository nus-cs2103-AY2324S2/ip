package exceptions;

/**
 * An exception indicating the detection of an invalid date.
 * This exception is specific to the lulu.Lulu application and is a subclass of {@link LuluException}.
 */
public class InvalidDateException extends LuluException {
    public InvalidDateException() {
        super("Invalid date detected");
    }
}
