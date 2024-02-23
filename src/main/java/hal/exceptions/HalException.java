package hal.exceptions;

/**
 * The `HalException` class represents any exception that is defined for the HAL9000 chat-bot.
 */
public class HalException extends Exception {
    public HalException(String message) {
        super(message);
    }

    public HalException(String message, Throwable cause) {
        super(message, cause);
    }

}
