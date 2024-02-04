package duke.exceptions;

/**
 * Checked exception thrown typically due to issues that affect the behaviour of the chatbot
 */
public class MeanDukeException extends Exception {

    public MeanDukeException(String message) {
        super(message);
    }

    public MeanDukeException() {
        super();
    }
}
