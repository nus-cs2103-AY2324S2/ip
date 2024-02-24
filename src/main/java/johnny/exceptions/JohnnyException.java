package johnny.exceptions;

/**
 * Custom exception thrown by methods in Johnny chatbot.
 */
public class JohnnyException extends Exception {

    /**
     * Constructor to create Exception.
     *
     * @param errorMessage Error message to be printed by Ui when caught.
     */
    public JohnnyException(String errorMessage) {
        super(errorMessage);
    }

}
