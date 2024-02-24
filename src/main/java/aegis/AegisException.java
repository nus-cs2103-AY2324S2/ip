package aegis;

/**
 * Represents an exception that is specific to the Aegis assistant program.
 */
public class AegisException extends Exception {
    /**
     * Constructor for creating an AegisException object.
     *
     * @param message String containing more detailed information about the exception.
     */
    public AegisException(String message) {
        super(message);
    }
}
