package exceptions;

/**
 * An exception indicating the inability to decipher the input string.
 * This exception is specific to the lulu.Lulu application and is a subclass of {@link LuluException}.
 */
public class InvalidCommandException extends LuluException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
