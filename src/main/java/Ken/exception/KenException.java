package ken.exception;

/**
 * The KenException class represents an exception specific to the Ken Application
 * It extends the base Exception class.
 */
public class KenException extends Exception {

    /**
     * Constructs a new KenException with the specified detail message.
     *
     * @param message the detail message
     */
    public KenException(String message) {
        super(message);
    }
}
