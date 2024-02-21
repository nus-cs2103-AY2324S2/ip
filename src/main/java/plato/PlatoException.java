package plato;

/**
 * Exception class handle special cases of exception thrown by the plato chatbot.
 */
public class PlatoException extends Exception {
    /**
     * Initialises the exception.
     *
     * @param msg exception message to figure out what is wrong.
     */
    public PlatoException(String msg) {
        super(msg);
    }
}
