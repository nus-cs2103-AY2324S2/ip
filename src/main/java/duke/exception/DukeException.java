package duke.exception;

/**
 * The class representing the parent exception of this chatbot.
 * */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
