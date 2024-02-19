package lamball.exception;

/**
 * An exception that is thrown when the user inputs invalid commands.
 *
 * @author ongzhili
 */
public class LamballParseException extends Exception {
    /**
     * Constructor for Lamball parse exception.
     *
     * @param message Error message.
     */
    public LamballParseException(String message) {
        super(message);
    }
}
