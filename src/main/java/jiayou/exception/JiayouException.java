package jiayou.exception;

/**
 * Represents a customized exception for the chatbot jiayou.
 * @author Liu Jiayao
 */
public class JiayouException extends Exception {
    /**
     * Returns a JiayouException instance with the given error message.
     *
     * @param message the description of the exception.
     */
    public JiayouException(String message) {
        super(message);
    }
}
