/**
 * Checked exception thrown typically due to issues that affect the behaviour of the chatbot
 */
public class MeanDukeException extends Exception {

    /**
     * Constructs an instance of this class.
     *
     * @param message A string containing details of the cause of this exception
     */
    public MeanDukeException(String message) {
        super(message);
    }
}
