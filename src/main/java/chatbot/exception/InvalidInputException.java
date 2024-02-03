package chatbot.exception;

import java.io.IOException;

/**
 * Represents an exception when the input is invalid.
 */
public class InvalidInputException extends IOException {

    /**
     * Constructor for InvalidInputException.
     * @param message The message to be displayed.
     */
    public InvalidInputException(String message) {
        super(message);
    }

}
