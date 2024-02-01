package duke.exceptions;

/**
 * The `HalException` class represents any exception that is defined for the HAL9000 chatbot.
 */
public class HalException extends Exception {
    public HalException(String message) {
        super(message);
    }

    public HalException(String message, Throwable cause) {
        super(message, cause);
    }

}
