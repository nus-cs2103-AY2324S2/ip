package seiki.data.exception;

/**
 * Custom exception thrown by methods in the chatbot.
 */
public class SeikiException extends Exception {
    public SeikiException(String msg) {
        super(msg);
    }
}
