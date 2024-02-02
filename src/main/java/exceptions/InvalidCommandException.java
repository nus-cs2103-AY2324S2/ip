package exceptions;

/**
 * An exception indicating the inability to decipher the input string.
 * This exception is specific to the Lulu application and is a subclass of {@link LuluException}.
 */
public class InvalidCommandException extends LuluException {
    public InvalidCommandException() {
        super("Could not decipher input string");
    }
}
