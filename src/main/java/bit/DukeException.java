package bit;

/**
 * Special exception thrown for chatbot
 */
public class DukeException extends Exception {
    /**
     * This is a unique exception for chatbot
     * @param errorMessage message to be printed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
