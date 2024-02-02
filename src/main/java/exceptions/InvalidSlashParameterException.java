package exceptions;

/**
 * An exception indicating an inability to decipher the command after a slash in the input.
 * This exception is specific to the Lulu application and is a subclass of {@link LuluException}.
 */
public class InvalidSlashParameterException extends LuluException {
    public InvalidSlashParameterException() {
        super("Could not decipher command after slash");
    }
}
