package checkbot.exception;

/**
 * Represents an exception that is thrown by Checkbot.
 */
public abstract class CheckbotException extends Exception {
    public CheckbotException(String err) {
        super(err);
    }
}
