package exceptions;

/**
 * An exception indicating an inability to decipher the command after a slash in the input.
 * This exception is specific to the lulu.Lulu application and is a subclass of {@link LuluException}.
 */
public class InvalidSlashParameterException extends LuluException {
    public InvalidSlashParameterException(String message) {
        super(message);
    }
}
