package meanduke.exceptions;

/**
 * Checked exception thrown typically due to issues that affect the behaviour of the chatbot
 */
public class MeanDukeException extends Exception {

    /**
     * Constructs an MeanDukeException class with a specific message.
     *
     * @param message The message to be tagged to this exception.
     */
    public MeanDukeException(String message) {
        super(message);
    }

    /**
     * Constructs a MeanDukeException with no specific message.
     */
    public MeanDukeException() {
        super();
    }
}
