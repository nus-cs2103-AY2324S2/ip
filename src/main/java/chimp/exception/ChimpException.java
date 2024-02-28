package chimp.exception;

/**
 * The base class for all Chimp exceptions.
 */
public abstract class ChimpException extends Exception {

    /**
     * Constructs a new ChimpException with the specified detail message.
     *
     * @param message the detail message.
     */
    ChimpException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public abstract String toString();
}
