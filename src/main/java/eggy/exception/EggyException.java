package eggy.exception;

/**
 * Represents an exception by Eggy.
 */
public class EggyException extends Exception {
    /**
     * Constructs an EggyException with the specified message.
     *
     * @param message The message.
     */
    public EggyException(String message) {
        super("EGGIES!!!" + message);
    }
}
