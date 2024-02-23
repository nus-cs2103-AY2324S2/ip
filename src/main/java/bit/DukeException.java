package bit;

/**
 * Special exception thrown for chatbot
 */
public class DukeException extends Exception {
    /**
     * Gets thrown when appropriate
     *
     * @param errorMessage message to be printed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
